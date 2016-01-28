logger = new com.cigna.utils.JenkinsLogger();

standardCignaBuild {

    envVars = {
        mavenVersion = 'Maven 3.3';
        jdkVersion = 'JDK8'
        //OS = 'windows'
    }

    preBuild = {
        println 'pre build from dev'
    }

    build = {
        println 'Build some project'
        println 'git repo'
        println 'compile & build'
        
        git 'https://github.com/jglick/simple-maven-project-with-tests.git'
        logger.log('pulled from GIT repo');
        
        //mvn(mavenVersion, '-B -Dmaven.test.failure.ignore verify');
        def mvnHome = tool mavenVersion
        sh "${mvnHome}/bin/mvn -B -Dmaven.test.failure.ignore verify"
        
    }

    postBuild = {
        println 'post build from dev'
        step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])        
    }

}
