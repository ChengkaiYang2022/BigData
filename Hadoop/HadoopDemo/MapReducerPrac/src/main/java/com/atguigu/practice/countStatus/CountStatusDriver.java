package com.atguigu.practice.countStatus;

import com.atguigu.practice.bean.NginxBean;
import com.atguigu.practice.outputformat.PlainFileOutputFomat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class CountStatusDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        job.setJarByClass(CountStatusDriver.class);

        job.setMapperClass(CountStatusMapper.class);
        job.setReducerClass(CountStatusReducer.class);
        job.setCombinerClass(CountStatusCombine.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setOutputFormatClass(PlainFileOutputFomat.class);

        String input = "MapReducerPrac/src/main/resources/safe_interface2.json";
        String output = "MapReducerPrac/src/main/resources/output/count_status";

        if(args.length == 2){
            input = args[0];
            output = args[1];
        }
        job.setOutputFormatClass(PlainFileOutputFomat.class);
        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
