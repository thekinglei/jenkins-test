import jenkins
jenkins_url="test.jenkins.com"
jenkins_user="test"
jenkins_password="pass"
server = jenkins.Jenkins(jenkins_url, jenkins_user, jenkins_password)

job_lists = server.get_jobs()
print jjob_lists
