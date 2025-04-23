package org.code;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import cascading.flow.Flow;
import cascading.flow.local.LocalFlowConnector;
import cascading.operation.aggregator.Count;
import cascading.operation.regex.RegexSplitGenerator;
import cascading.pipe.Each;
import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.property.AppProps;
import cascading.scheme.local.TextLine;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.local.FileTap;
import cascading.tuple.Fields;

import java.util.Properties;

public class CascadingLocalWordCounter {

    public static void main(String[] args) {

        //input and output path
        String inputPath = args[0];
        String outputPath = args[1];
        Tap srctap = new FileTap(new TextLine(new Fields("line")), inputPath);
        Tap sinkTap = new FileTap(new TextLine(new Fields("word", "count")), outputPath, SinkMode.REPLACE);

        Pipe words = new Each("words", new RegexSplitGenerator("\\s+"));
        Pipe group = new GroupBy(words);
        Pipe wcount = new Every(group, new Count());
        Properties properties = new Properties();
        AppProps.setApplicationJarClass(properties, CascadingLocalWordCounter.class);
        LocalFlowConnector flowConnector = new LocalFlowConnector();
        Flow flow = flowConnector.connect("cascading wordcount job", srctap, sinkTap, wcount);
        flow.complete();
    }
}