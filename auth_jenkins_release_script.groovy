import hudson.security.AuthorizationMatrixProperty
import jenkins.*
import jenkins.model.*

def jen = Jenkins.getInstance();
//exec_users_list:具有可执行权限的用户列表
exec_users_list=["test"]
//read_users_list:具有查看权限的用户列表
read_users_list=[""]
//list_view:子视图
list_view = ["animal"]
//job_start_name_list:所有子视图下
job_start_name_list=["BUILD"]

for (i in list_view){
for (j in job_start_name_list){

jen.getView("father_view").getView(i).items.each {jobToUpdate ->

  if(jobToUpdate.name.startsWith(j)){
		def authorToUpdate = jobToUpdate.getProperty(AuthorizationMatrixProperty.class)
	if (authorToUpdate != null )
	{
	 	jobToUpdate.removeProperty(hudson.security.AuthorizationMatrixProperty)
	}

	jobToUpdate.addProperty(new hudson.security.AuthorizationMatrixProperty())
	authorToUpdate = jobToUpdate.getProperty(AuthorizationMatrixProperty.class)

   exec_users_list.each {
  
    println it
     
    authorToUpdate.add("hudson.model.Item.Read:$it")
    authorToUpdate.add("hudson.model.Item.Build:$it")
    authorToUpdate.add("hudson.model.Item.Cancel:$it")
    authorToUpdate.add("hudson.model.Item.Workspace:$it")
    authorToUpdate.add("hudson.model.Item.Discover:$it")
  
  }
   read_users_list.each {

    println it

    authorToUpdate.add("hudson.model.Item.Read:$it")
    authorToUpdate.add("hudson.model.Item.Workspace:$it")
    authorToUpdate.add("hudson.model.Item.Discover:$it")

  }  


    jobToUpdate.save()

	println("the permissions after update: ${authorToUpdate.getGrantedPermissions()}" )
  }

}


}
}
