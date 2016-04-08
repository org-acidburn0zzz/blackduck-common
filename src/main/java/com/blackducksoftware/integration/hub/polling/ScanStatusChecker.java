/*******************************************************************************
 * Black Duck Software Suite SDK
 * Copyright (C) 2016 Black Duck Software, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *******************************************************************************/
package com.blackducksoftware.integration.hub.polling;

import java.util.concurrent.CountDownLatch;

import com.blackducksoftware.integration.hub.HubIntRestService;
import com.blackducksoftware.integration.hub.exception.HubIntegrationException;
import com.blackducksoftware.integration.hub.scan.status.ScanStatus;
import com.blackducksoftware.integration.hub.scan.status.ScanStatusToPoll;

public class ScanStatusChecker implements Runnable {

	private ScanStatusToPoll currentStatus;

	private final CountDownLatch countDownLock;

	private final HubIntRestService service;

	private HubIntegrationException exception;

	private boolean running;

	public ScanStatusChecker(final HubIntRestService service, final ScanStatusToPoll currentStatus, final CountDownLatch countDownLock) {
		this.service = service;
		this.currentStatus = currentStatus;
		this.countDownLock = countDownLock;
		running = true;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(final boolean running) {
		this.running = running;
	}

	public boolean hasError() {
		return exception != null;
	}

	public HubIntegrationException getError() {
		return exception;
	}

	private boolean isScanFinished(final ScanStatusToPoll status) {
		if (ScanStatus.isFinishedStatus(currentStatus.getStatusEnum()) == false) {
			return false;
		} else {

			if (ScanStatus.isErrorStatus(currentStatus.getStatusEnum())) {
				exception = new HubIntegrationException("There was a problem with one of the scans. Error Status : "
						+ currentStatus.getStatusEnum().name());
			}
			countDownLock.countDown(); // finished so unlock the parent thread.
			setRunning(false);
			return true;
		}

	}

	@Override
	public void run() {
		while (isRunning() == true) {

			try {
				if (isScanFinished(currentStatus) == true) {
					break;
				} else {
					// The code location is still updating or matching, etc.
					currentStatus = service.checkScanStatus(currentStatus.get_meta().getHref());

					if (isScanFinished(currentStatus) == true) {
						break;
					}
				}

				Thread.sleep(10000);
			} catch (final Exception ex) {
				setRunning(false);
			}
		}
	}
}
