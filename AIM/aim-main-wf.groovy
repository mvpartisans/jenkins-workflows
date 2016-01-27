standardCignaBuild {

    envVars = {
        mavenVersion = '3.3';
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