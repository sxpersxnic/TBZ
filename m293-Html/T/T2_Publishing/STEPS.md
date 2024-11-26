# T2 Exercises

### Exercise 1: Create Web Hosting and Manually Publish Website

1. Create a web hosting account on a free service like bplaced.net. Bplaced.net offers a free hosting option called "FreeStyle".
2. Find the FTP settings on the bplaced.net help page.
3. Install one of the mentioned FTP tools (e.g., FileZilla).
4. Upload your existing website to bplaced.net using the FTP tool.

### Exercise 2: Automate Publishing

1. Automate the publishing process using one of the mentioned tools (or another tool of your choice).
2. Create a Batch/Bash/Exe file that allows you to publish your site "at the push of a button".
3. Your program should have a configuration part at the beginning that contains the settings (server, port, username, password, local path, remote path).
4. Then, the script should upload the files from the local path to the web server via SFTP or FTPS.

You can also create a Java, Python, or C# project if you want, but it might be a bit more effort.

### Optional Exercise 3: Publishing via GitLab/GitHub

This variant is significantly more complex than Exercise 2 and serves as advanced content that you can optionally solve.

1. Heroku offers a cloud environment where it is possible to publish applications in various programming languages. They offer a free version and are therefore used here.
2. Publish the site on Heroku as an app via GitLab/GitHub CI/CD tools. You can find corresponding instructions here:
   - Heroku CLI Guide
   - GitLab Guide to Heroku

**NOTE**: On Heroku, you can only publish dynamic applications. For pure HTML pages, an error is thrown. The trick here is to suggest that it is a dynamic page. You can find a corresponding guide here.
