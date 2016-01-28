    //jira = new Jira()
    //jira.notify()
    //println "noified Jira"
    
    acme = new com.cigna.utils.Acme();
    acme.say('imran');
    
    jira = new com.cigna.utils.Jira();
    jira.notification();


standardCignaBuild {

    envVars = {
        mavenVersion = 'Maven 3.3';
        jdkVersion = 'JDK8'
    }

    preBuild = {
        println 'pre build from dev'
    }

    build = {
        println 'Build some project'
        println 'git repo'
        println 'compile & build'
        
        git 'https://github.com/jglick/simple-maven-project-with-tests.git'
        mvn('-B -Dmaven.test.failure.ignore verify')

        //step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])

    }

    postBuild = {
        println 'post build from dev'
    }

}
