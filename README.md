<p align="center">![Mockbin CLI](http://i.imgur.com/CTs32G9.png)

**Mockbin CLI** is a [Bootstrap Shell](https://github.com/robinhowlett/spring-social-bootstrap/tree/master/bootstrap-shell)-based CLI application for [Mockbin](http://mockbin.com/) powered by the [Spring Social Mockbin](https://github.com/robinhowlett/spring-social-mockbin) API client (which itself is based on [Spring Social Bootstrap SDK](https://github.com/robinhowlett/spring-social-bootstrap/tree/master/spring-social-bootstrap-sdk))

![CLI Example](http://i.imgur.com/8Eca4p3.gif)

## Using

Add the following repository to your `pom.xml`:

	<repositories>
		<repository>
			<id>s3.release</id>
			<name>Robin's S3 Release Repository</name>
			<url>http://maven.robinhowlett.com/release</url>
			<releases>
				<enabled>true</enabled>
			</releases>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>

and the following dependency:

    <dependency>
        <groupId>com.sportslabs.amp</groupId>
        <artifactId>mockbin-cli</artifactId>
        <version>3</version>
    </dependency>
