package com.traceroot.dataobject.multikeysclass;

import com.traceroot.dataobject.PipelineSegment;
import lombok.Data;

import java.io.Serializable;

/**
 * PipelineSegment的复合主键类
 *
 *  @Param segmentId
 *  @Param segmentSerialNumber
 *  由这两个共同组成复合主键
 */
@Data
public class PipelineSegmentMultiKeys implements Serializable {

    private String segmentId;

    private Integer segmentSerialNumber;

    public PipelineSegmentMultiKeys(){}

    public PipelineSegmentMultiKeys(String segmentId, Integer segmentSerialNumber) {
        this.segmentId = segmentId;
        this.segmentSerialNumber = segmentSerialNumber;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((segmentId == null) ? 0 : segmentId.hashCode());
        result = PRIME * result + ((segmentSerialNumber == null) ? 0 : segmentSerialNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final PipelineSegmentMultiKeys other = (PipelineSegmentMultiKeys)obj;
        if(segmentId == null){
            if(other.segmentId != null){
                return false;
            }
        }else if(!segmentId.equals(other.segmentId)){
            return false;
        }
        if(segmentSerialNumber == null){
            if(other.segmentSerialNumber != null){
                return false;
            }
        }else if(!segmentSerialNumber.equals(other.segmentSerialNumber)){
            return false;
        }
        return true;
    }
}
