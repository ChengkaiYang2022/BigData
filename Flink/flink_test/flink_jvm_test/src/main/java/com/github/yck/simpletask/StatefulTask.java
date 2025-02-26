package com.github.yck.simpletask;

import com.github.yck.datasource.DataGenerateStrategy;
import com.github.yck.datasource.WebLogDataGenerator;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 *
 */
public class StatefulTask {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<String> s = env.addSource(
                new WebLogDataGenerator(10, 100, DataGenerateStrategy.RANDOM_NUMBER)
                , "GenerateWebLogRandomly");
        s.map(new MapFunction<String, Tuple2<String,Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String s) throws Exception {
                        return new Tuple2<>(s,1);
                    }
                }).keyBy(new KeySelector<Tuple2<String, Integer>, Object>() {
                    @Override
                    public Object getKey(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                        return stringIntegerTuple2.f0;
                    }
                })
                .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                .sum(1).map(
                        new MapFunction<Tuple2<String, Integer>, String>() {
                            @Override
                            public String map(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                                return "链接+"+stringIntegerTuple2.f0+"的访问次数为"+stringIntegerTuple2.f1;
                            }
                        }
                ).print();
        env.execute("定时统计用户的访问次数,更好的输出");
    }
}
