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
        println 'Build some project'
        println 'git repo'
        println 'compile & build'
        
        git 'https://github.com/jglick/simple-maven-project-with-tests.git'
        
        audit.log([category:"build",
                who:"imran",
                what:"git checkout"])
        
        //mvn(mavenVersion, '-B -Dmaven.test.failure.ignore verify');
        def mvnHome = tool mavenVersion
        sh "${mvnHome}/bin/mvn -B -Dmaven.test.failure.ignore verify"
        
    }

    postBuild = {
        println 'post build from dev'
        step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])        
    }

}
