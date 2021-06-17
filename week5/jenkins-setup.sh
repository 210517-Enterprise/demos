# First, you should make sure that you've followed the basic setup from the ec2-tomcat-setupguide.sh (go back to week5 directory)
# If Tomcat is currently installed on your EC2, you should switch its port from 8080 to 8085,
# so that Jenkins can run on 8085.  Run the follow command:

sudo sed -i 's/<Connector port=\"8080\"/<Connector port=\"8085\"/' /etc/tomcat/server.xml

# Restart Tomcat if you're currently running it
sudo service tomcat restart

# Now for the Jenkins stuff...install Jenkins via the following commands (as per Jenkins documentation)
sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins.io/redhat-stable/jenkins.repo

# Generate the Jenkins private key to log into the dashboard
sudo rpm --import http://pkg.jenkins.io/redhat-stable/jenkins.io.key

# Install it on your instance
sudo yum install -y jenkins

# Start the service which will run on port 8080 automatically
sudo service jenkins start

# Access the Dashboard by going to http://your-ec2-dns.amazonaws.com:8080

# You will be asked for a password...to fetch it, in your EC2 shell run:
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

# Copy this password and paste it into the dashboard - Now you're in!
# You can create your Admin account to log back in with a simpler password

# When you're in the Jenkins dashboard, do the following:
# Click new item > Name it > Select "Freestyle Project which allows SCM".
# Under Source Code Management click "Git".
# Enter your Repository URL (NOT ending with .git -- it's straigh from the address bar).
# Make sure that the branch points to "main" or wherever your code is.
# Under "Build Triggers" check "GitHub hook trigger for GITScm Pooling"
# Under Build click Add Build Step > Execute shell > Paste the following
#
# cd <YourProjectName>
# mvn clean install
# mvn -Dtest=SomeTestSuiteClass test
#
# Save and manually configure build by clicking "Build Now" on the Freestyle Project Dashboard.
#
# If you'd like to configure GitHub Webhooks, you can do so by going to your gihub repo > settings > add Webhooks
# Click "Add" 
# In the Payload Url enter: http://your.ec2.public.ipv4.address:8080/github-webhook/
# Change the content to JSON
# Check to make sure that PUSH triggers the payload request
#
# Now, whenever you push, it will trigger a Jenkins build!
