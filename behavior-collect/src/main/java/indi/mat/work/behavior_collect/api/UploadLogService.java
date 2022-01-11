package indi.mat.work.behavior_collect.api;

import java.util.List;

import indi.mat.work.behavior_collect.entity.EventPoint;

public interface UploadLogService {
    void upload(List<EventPoint> eventPointList);
}
