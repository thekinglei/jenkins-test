import hudson.security.AuthorizationMatrixProperty
def jen = Jenkins.getInstance();
 
 
 
 
jen.getView("bb").items.each {jobToUpdate ->
 
println jobToUpdate.name
def authorToUpdate = jobToUpdate.getProperty(AuthorizationMatrixProperty.class)
if (authorToUpdate != null )
{
          jobToUpdate.removeProperty(hudson.security.AuthorizationMatrixProperty)
}
 
jobToUpdate.addProperty(new hudson.security.AuthorizationMatrixProperty())
authorToUpdate = jobToUpdate.getProperty(AuthorizationMatrixProperty.class)
 
   ["test3","test2","test1"].each {
 
    println it
    
authorToUpdate.add("hudson.model.Item.Read:$it")
authorToUpdate.add("hudson.model.Item.Build:$it")
authorToUpdate.add("hudson.model.Item.Cancel:$it")
authorToUpdate.add("hudson.model.Item.Workspace:$it")
authorToUpdate.add("hudson.model.Item.Discover:$it")
 
  }
 
 
 
 
jobToUpdate.save()
 
println("the permissions after update: ${authorToUpdate.getGrantedPermissions()}" )
 
}
