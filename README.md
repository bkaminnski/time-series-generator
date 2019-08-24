# Time Series Generator

Generates time series:
- between two dates expressed as `yyyy-MM-dd` in local time, 
- giving a total of `X`,
- limiting values throughout the whole period and throughout the day according to [Limited Randoms Generator](https://github.com/bkaminnski/limited-randoms).

## Usage

```
mvn clean install
java -jar .\target\timeseriesgenerator-0.0.1-SNAPSHOT-jar-with-dependencies.jar 2019-07-01 2019-08-01 255 3;2;3;3;2;4;3;3;2;3;4;5;4;4;3;4;2;4 1;1;1;1;1;3;5;2;1;4;3;3;1;1;7;8;6;9;7;8;9;5;3;1
```

![Chart](readme/chart.png)