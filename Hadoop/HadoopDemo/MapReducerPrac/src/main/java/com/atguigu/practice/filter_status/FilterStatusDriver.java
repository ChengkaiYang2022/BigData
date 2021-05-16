package com.atguigu.practice.filter_status;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FilterStatusDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(FilterStatusDriver.class);
        job.setMapperClass(FilterStatusMapper.class);
        job.setReducerClass(FilterStatusReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NginxBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setOutputFormatClass(PlainFileOutputFomat.class);
        FileInputFormat.setInputPaths(job, new Path("/home/yzf/Bigdata/Hadoop/HadoopDemo/MapReducerPrac/src/main/resources/safe_interface2.json"));
        FileOutputFormat.setOutputPath(job, new Path("/home/yzf/Bigdata/Hadoop/HadoopDemo/MapReducerPrac/src/main/resources/output/filter_status"));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
