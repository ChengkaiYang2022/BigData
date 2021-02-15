package com.atguigu.flink.stream;

import com.atguigu.flink.beans.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

public class SourceTest1_Collection {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<SensorReading> sensorReadingDataStream =  env.fromCollection(
                Arrays.asList(
                        new SensorReading("sensor_1",1547718199L,35.8),
                        new SensorReading("sensor_6",1547718201L,15.4),
                        new SensorReading("sensor_7",1547718202L,6.7),
                        new SensorReading("sensor_10",1547718205L,38.1)
                )
        );
        env.setParallelism(1);
        sensorReadingDataStream.print("sensor");
        env.execute("");
    }
}
