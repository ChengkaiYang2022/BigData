package com.atguigu.practice.countCompany;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CountCompanyCombiner extends Reducer<Text, LongWritable, Text, LongWritable> {
    private LongWritable outV = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long sum = 0;
        for (LongWritable value : values) {
            sum += value.get();
        }
        outV.set(sum);
        context.write(key, outV);

    }
}
