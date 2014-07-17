datamanager-maven-plugin
========================

A Maven plugin for generating the XSD files from JPA classes using a Maven plugin.

Usage
=====
* First download the project source from this github repo using either the Download ZIP link at the bottom right or cloning this repository.
* Next run the following command to install the plugin to your local repo:
	        mvn clean install
	        
You can also deploy the plugin, if you want to your remote repository using the deploy goal of Maven.

* Next, include this plugin definition in your pom file :

            <plugin>
                <groupId>org.easetech.plugin</groupId>
                <artifactId>datamanager-maven-plugin</artifactId>
                <version>1.0-SNAPSHOT</version> <!-- look for the latest version -->
                <executions>
                    <execution>
                        <phase>generate-resources</phase>
                            <goals>
                                <goal>generateXSD</goal>
                            </goals>
                    </execution>
                </executions>
                <configuration>
                    <packageName>org.example.dao.model</packageName>
                    <xsdOutputDirectory>${project.basedir}/src/main/resources/org/xsd</xsdOutputDirectory>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.sample</groupId>
                        <artifactId>your-dao</artifactId>
                        <version>1.0-SNAPSHOT</version>
                    </dependency>
                </dependencies>
            </plugin>
            
            
In case the JPA objects are present in separate JAR file, just include that JAR file as dependency in the dependencies section of the plugin.

You can remove the executions section all together from the above plugin configuration and then you can call the generateXSD goal using the Maven command :

         mvn et:generateXSD -DoutputDir=xsd.output.dir -DjpaPackage=package.containing.jpa.model.classes
         
where et stands for EaseTech.

NOTE: The XSD outputDir should be already there, otherwise the MOJO will throw an exception.