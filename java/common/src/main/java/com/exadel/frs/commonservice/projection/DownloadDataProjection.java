package com.exadel.frs.commonservice.projection;

public record DownloadDataProjection(long timestamp, int deviceId, String userName, int gender, float similarity, String captureImgUrl, byte[] faceImg)
{
}
