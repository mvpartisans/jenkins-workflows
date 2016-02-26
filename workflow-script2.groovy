import groovy.json.JsonOutput;
    User user= User.current();
    println user.getDisplayName();
    
node {

   stage 'Stage 1'
   echo 'Hello World 1'
   stage 'Stage 2'
   echo 'Hello World 2'
   //log(["category": "scan", "key": "fortify-dynamic", "value" : "true" ])
   def slaveLabels = "windows, java"
    log([category:"build", who:"imran", what:"Used Label ${slaveLabels}"]);   
    //echo "build id :${env.BUILD_NUMBER}"
    echo "current build : ${currentBuild}"
    echo "current build ${env.BUILD_NUMBER}"
    echo "current build URL :  ${env.BUILD_URL}"
    echo "current JobName :  ${env.JOB_NAME}"
    
    //log();
}

void log(obj) {
        def str = JsonOutput.toJson(obj)
        println "log data : ${str}"
        def ELASTIC_SEARCH = "http://localhost:9200"
        
        def url = "${ELASTIC_SEARCH}/audit/${env.JOB_NAME}/${env.BUILD_NUMBER}"

        sh """
curl -H "Content-Type: application/json" -X PUT -d '${str}' ${url}
"""
}
