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
package com.blackducksoftware.integration.hub.scan.api;

import java.util.List;

public class ScanLocationItem {

	private String id;

	private String scanId;

	private String host;

	private String path;

	private String scanInitiatorName;

	private String lastScanUploadDate;

	private String scanTime;

	private List<ScanHistoryItem> scanList;

	private List<AssetReferenceItem> assetReferenceList;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getScanId() {
		return scanId;
	}

	public void setScanId(final String scanId) {
		this.scanId = scanId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(final String host) {
		this.host = host;
	}

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	public String getScanInitiatorName() {
		return scanInitiatorName;
	}

	public void setScanInitiatorName(final String scanInitiatorName) {
		this.scanInitiatorName = scanInitiatorName;
	}

	public String getLastScanUploadDate() {
		return lastScanUploadDate;
	}

	public void setLastScanUploadDate(final String lastScanUploadDate) {
		this.lastScanUploadDate = lastScanUploadDate;
	}

	public String getScanTime() {
		return scanTime;
	}

	public void setScanTime(final String scanTime) {
		this.scanTime = scanTime;
	}

	public List<AssetReferenceItem> getAssetReferenceList() {
		return assetReferenceList;
	}

	public void setAssetReferenceList(final List<AssetReferenceItem> assetReferenceList) {
		this.assetReferenceList = assetReferenceList;
	}

	public List<ScanHistoryItem> getScanList() {
		return scanList;
	}

	public void setScanList(final List<ScanHistoryItem> scanList) {
		this.scanList = scanList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetReferenceList == null) ? 0 : assetReferenceList.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastScanUploadDate == null) ? 0 : lastScanUploadDate.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((scanId == null) ? 0 : scanId.hashCode());
		result = prime * result + ((scanInitiatorName == null) ? 0 : scanInitiatorName.hashCode());
		result = prime * result + ((scanList == null) ? 0 : scanList.hashCode());
		result = prime * result + ((scanTime == null) ? 0 : scanTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ScanLocationItem)) {
			return false;
		}
		final ScanLocationItem other = (ScanLocationItem) obj;
		if (assetReferenceList == null) {
			if (other.assetReferenceList != null) {
				return false;
			}
		} else if (!assetReferenceList.equals(other.assetReferenceList)) {
			return false;
		}
		if (host == null) {
			if (other.host != null) {
				return false;
			}
		} else if (!host.equals(other.host)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (lastScanUploadDate == null) {
			if (other.lastScanUploadDate != null) {
				return false;
			}
		} else if (!lastScanUploadDate.equals(other.lastScanUploadDate)) {
			return false;
		}
		if (path == null) {
			if (other.path != null) {
				return false;
			}
		} else if (!path.equals(other.path)) {
			return false;
		}
		if (scanId == null) {
			if (other.scanId != null) {
				return false;
			}
		} else if (!scanId.equals(other.scanId)) {
			return false;
		}
		if (scanInitiatorName == null) {
			if (other.scanInitiatorName != null) {
				return false;
			}
		} else if (!scanInitiatorName.equals(other.scanInitiatorName)) {
			return false;
		}
		if (scanList == null) {
			if (other.scanList != null) {
				return false;
			}
		} else if (!scanList.equals(other.scanList)) {
			return false;
		}
		if (scanTime == null) {
			if (other.scanTime != null) {
				return false;
			}
		} else if (!scanTime.equals(other.scanTime)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ScanLocationItem [id=");
		builder.append(id);
		builder.append(", scanId=");
		builder.append(scanId);
		builder.append(", host=");
		builder.append(host);
		builder.append(", path=");
		builder.append(path);
		builder.append(", scanInitiatorName=");
		builder.append(scanInitiatorName);
		builder.append(", lastScanUploadDate=");
		builder.append(lastScanUploadDate);
		builder.append(", scanTime=");
		builder.append(scanTime);
		builder.append(", scanList=");
		builder.append(scanList);
		builder.append(", assetReferenceList=");
		builder.append(assetReferenceList);
		builder.append("]");
		return builder.toString();
	}

}
