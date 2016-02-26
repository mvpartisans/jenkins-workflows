import groovy.json.JsonOutput;

node {
    
   // Mark the code checkout 'stage'....
   stage 'Checkout'

   // Get some code from a GitHub repository
//   git url: 'https://github.com/jglick/simple-maven-project-with-tests.git'
   git url: 'https://github.com/mvpartisans/jenkins-workflows'
   
println 'getting changesets'   
   def jsonStr = JsonOutput.toJson(getChangeSet())
    println jsonStr
}


def getChangeSet(){
def changeSets= currentBuild.getRawBuild().getChangeSets()
List changeList = new ArrayList();
changeSets.each {changeSet ->
    changeSet.each{
        Map changes = new HashMap();        
        changes.put("committer", it.committer);
        changes.put("title", it.title);        
        changes.put("date", it.date);                
        changeList.add(changes);
    }
}    
 return changeList;   
}
