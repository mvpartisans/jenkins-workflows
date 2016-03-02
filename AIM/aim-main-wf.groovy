audit = new com.cloudbees.utils.AuditTrail();

standardBuild {

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
        
        audit.log([category:"scm", who:"imran", what:"git ${scmUrl}"]);
        mvn(mavenVersion, '-B -Dmaven.test.failure.ignore verify');
    }

    postBuild = {
        audit.log([category:"build", who:"imran", what:"archived artifact"]);
        archiveArtifact('**/target/*.jar');
    }

}
