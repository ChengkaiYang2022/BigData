package mapreduce.combiner;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WcDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1、获取一个job实例
        Job job = Job.getInstance();

        // 2、设置类路径（classpath）
        job.setJarByClass(WcDriver.class);
        // 3、设置Mapper和Reducer
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);

        // 4、设置Mapper和Reducer输出的类型

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 5、设置输入输出的数据
        job.setCombinerClass(WcReducer.class);
//        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileInputFormat.setInputPaths(job, new Path("MapReducerDemo/src/main/resources/11_input/inputword/hello.txt"));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path("MapReducerDemo/src/main/resources/output/11_input/inputword"));
        // 6、提交job
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0:1);

    }
}
