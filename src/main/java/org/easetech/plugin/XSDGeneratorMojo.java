package org.easetech.plugin;


import org.apache.maven.plugins.annotations.LifecyclePhase;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.easetech.schemagenerator.JPAToXSD;

/**
 * Maven Goal to generate XSD files from JPA classes.
 * Look at {@link JPAToXSD} for details on the business logic.
 * This mojo expects two parameters:
 * <li> {@link #xsdOutputDirectory} - the directory where the XSD files will be generated.
 * This dir should be available before this goal is called, else the goal will fail.
 * <li> {@link #packageName} - the name of the package that contains all the model classes.
 * 
 * Curently this mojo is limited to taking only the name of the package containing JPA classes.
 * In future the mojo will be extended to be more fine grained.
 */
@Mojo( name = "generateXSD", defaultPhase=LifecyclePhase.GENERATE_RESOURCES)
public class XSDGeneratorMojo extends AbstractMojo
{
    /**
     * The output directory where the XSD files will be generated
     */
    @Parameter( defaultValue = "${project.build.directory}", property = "outputDir", required = true )
    private String xsdOutputDirectory;
    
    /**
     * The package name that contains all the JPA classes for which the XSD files need to be generated.
     */
    @Parameter(property="jpaPackage")
    private String packageName;


    @Override
    public void execute() throws MojoExecutionException
    {
        getLog().info("Generating XSD at location :" + xsdOutputDirectory);
        JPAToXSD.generateAndCreate(packageName, xsdOutputDirectory);
    }
}
