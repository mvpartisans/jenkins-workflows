    //jira = new Jira()
    //jira.notify()
    //println "noified Jira"
    
    acme = new utils.Acme();
    acme.say('imran');


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
    }

    postBuild = {
        println 'post build from dev'
    }

}
