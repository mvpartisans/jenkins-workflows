audit = new com.cigna.utils.AuditTrail();

standardCignaBuild {

    envVars = {
        mavenVersion = 'Maven 3.3';
        jdkVersion = 'JDK8'
        OS = 'master'
    }

    preBuild = {
        println 'pre build from dev'
    }

    build = {
        def scmUrl = 'https://github.com/jglick/simple-maven-project-with-tests.git'
        git scmUrl
        
        audit.log([category:"scm",
                who:"imran",
                what:"git ${scmUrl}"])
        
        //mvn(mavenVersion, '-B -Dmaven.test.failure.ignore verify');
        def mvnHome = tool mavenVersion
        sh "${mvnHome}/bin/mvn -B -Dmaven.test.failure.ignore verify"
        
    }

    postBuild = {
        println 'post build from dev'
        archiveArtifact('**/target/*.jar');
    }

}
