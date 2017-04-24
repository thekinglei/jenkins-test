#coding=utf-8

'''
gitlab operations:
    search group or projects
    create group or projects
'''

import jenkinsapi as ji
j_url = "http://jenkins.rd.cxxxxt.com"
user_id = "test"
api_token = "e72d4e6fd3ca67514e5ce0"
#j_url ="http://172.16.33.71:18080/jenkins"
#user_id="wangleiaf"
#api_token="d25aa1fab6b8a4cdc6cdf90a5418df02"
#复制job
#def copy(jobs_name):
server = ji.jenkins.Jenkins(j_url,username=user_id,password=api_token)
def copy(jobs_name):
    jobs = server.get_jobs_list()
    #print jobs
    for i in jobs:
        if i.find(jobs_name) == 0:
            #tmp = i.replace("TEST","INTE")
            tmp = i.replace("BUILD-to-TEST__paas", "TEST-to-INTE__paas")
            print i, tmp
            server.copy_job(i,tmp)


#生成rundeck-job
def build_job(jobname, para=None):
    Sid_list = "C7036 C6485 C6215 C1829 C0879 C8753 C8265 C8973 C3240 C5381 C1649 C5693 C1342 C8265 C6293 C7509 C1024 C3182 C7395 C5430 C4691 C3075 C0495 C4712"
    Sids = Sid_list.split(" ")
    #print Sids
    para = {"RD_ENVTYPE":"dev", "RD_PROJECT":"HSY_DEV2", "RD_GROUP":""}
    for i in Sids:
        tmp = para
        tmp["RD_JOBNAME"] = i
        print tmp
        server.build_job(jobname,params=para)

def build_job_dev2(jobs_name):
    jobs = server.get_jobs_list()
    #print jobs
    for i in jobs:
        if i.find(jobs_name) == 0:
            server.build_job(i, {"BRANCH":"develop"})

def enable_job(job_url):
    job_obj = server.job.Job("http://jenkins.rd.chanjet.com/view/CM/view/wangleiaf/job/wangleiaf_roll-back")

if __name__ == "__main__":
    #copy("BUILD-to-TEST__paas")
    #build_job("RUNDECK_NEWJOB2",{"RD_ENVTYPE":"dev", "RD_PROJECT":"HSY_DEV2", "RD_GROUP":"", "RD_JOBNAME":"wltest"})
    #build_job_dev2("BUILD-to-DEV2__paas")
    print "test"


