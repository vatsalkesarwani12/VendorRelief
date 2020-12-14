<p align="center">
  <a href="#"><img src="https://capsule-render.vercel.app/api?type=rect&color=ff0897&height=100&section=header&text=Vendor%20Relief&fontSize=50%&fontColor=ffffff%22%20alt=%22website%20title%20image" alt="website title image"></a>
  <h2 align="center">🏥 Android App for people of India during this Pandemic of Covid-19 🏥</h2>
</p>

1. During COVID-19, people living in societies and apartments may not be aware of the nearby shops and vendors whether they are providing essential services or not (such as Groceries, Pharmacy, Dairy, etc) and if they may be providing the people may not know the details of the vendors(like timings, contact number and address of the shop).
2. People may be facing some doubts about covid-19.
3. After knowing about these problems faced by some of the people ,we have done our best to help them out.

[Let’s see how……](https://drive.google.com/file/d/1jM2VANbsHNv4yrCOIaSbFtMtqQq1NZsp/view?usp=drivesdk)

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/plazzy99/Heal-O-Chat/blob/master/LICENSE)
![first-timers-only](https://img.shields.io/badge/first--timers--only-friendly-yellow.svg?style=flat)
![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat)
![Minimum API Level](https://img.shields.io/badge/Min%20API%20Level-21-green)
![Maximum API Level](https://img.shields.io/badge/Max%20API%20Level-29-orange)
![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)

Table of Contents
=================

   * [Features](#top-features-of-the-app)
   * [Tech Used](#tech-used)
   * [Requirements](#requirements)
   * [Discord Channel](#discord-channel)
   * [Getting Started](#getting-started)
      * [1. Star and Fork this Repository](#1-star-and-fork-this-repository)
      * [2. Clone the Repository](#2-clone-the-repository)
      * [3. Create New Branch](#3-create-new-branch)
      * [4. Commit and Push](#4-commit-and-push)
      * [5. Update Local Repository](#5-update-local-repository)
      * [6. Configure a Remote for the Fork](#6-configure-a-remote-for-the-fork)
      * [7. Sync the Fork](#7-sync-the-fork)
      * [8. Create Pull Request](#8-create-pull-request)
   * [Screenshorts of the app](#screenshots)
   * [Link to the website repository](#link-to-the-website-repository)
   * [Download app from the link](#download-app-from-the-link)
   * [Project Maintainer](#project-maintainer)

## Top Features of the App
1. Registered Vendors can provide their shop details by signing up.
2. Provide details of vendors to the customers who want to buy essential products for them like(Groceries, Medicines,dairy and others ).
3. Give real time data of COVID at global level and state level in India(details such as number of cases in world and India, number of deaths at globally & every state of India)
4. Details of several essential prevention and precaution to stay safe.

## Tech Used
1. Android
2. Java
3. Google Firebase Firestore
4. Google Analytics
5. Recycler View
6. Material I/O
7. Google Firebase Authentication

## Requirements 
1. Android Version 5.0 and above
2. compileSdkVersion 29
3. minSdkVersion 21

## Discord channel
[![chat on discord](https://img.shields.io/badge/chat-on%20discord-brightgreen)](https://discord.gg/DswQDgyF8a)

## Getting started

### 1. Star and Fork this Repository
###### You can star ⭐ and fork 🍽️ this repository on GitHub by navigating at the top of this repository.
![](./AppImages/star.png)


![](./AppImages/fork.png)
###### GitHub repository URLs will reference both the username associated with the owner of the repository, as well as the repository name. For example, plazzy99 is the owner of this repository, so the GitHub URL for this project is:

https://github.com/plazzy99/VendorRelief

###### When you’re on the main page for the repository, you’ll see a button to "Star" and “Fork” the repository on your upper right-hand side of the page, underneath your user icon.

### 2. Clone the Repository
###### To make your own local copy of the repository you would like to contribute to, let’s first open up a terminal window.
###### We’ll use the git clone command along with the URL that points to your fork of the repository.
###### This URL will be similar to the URL above, except now it will end with .git. In the example above, the URL will look like this:

https://github.com/plazzy99/VendorRelief

###### You can alternatively copy the URL by using the green “Clone or download” button from your repository page that you just forked from the original repository page. Once you click the button, you’ll be able to copy the URL by clicking the binder button next to the URL:
![](./AppImages/clone.png)

###### Once we have the URL, we’re ready to clone the repository. To do this, we’ll combine the git clone command with the repository URL from the command line in a terminal window:
```
git clone https://github.com/plazzy99/VendorRelief.git
```

### 3. Create New Branch
###### Once the project is opened create a new branch and checkout in it where you can make the changes in the code.
###### You can do this either from terminal or Directly in Android Studio.
###### To do from Terminal:
```
git branch new-branch
git checkout new-branch
```
###### To do directly from Android Studio
###### Click on Git branch in the bottom-right corner in Android Studio and create a new branch from there and checkout to it.
![](./AppImages/Change_branch.png)

### 4. Commit and Push
###### After making the required changes commit and push your code
###### Terminal:
###### To add the changes after you have made the modifications
``` git add . ``` or ``` git add -A ```
###### To commit and push the changes
```
git commit -m <Your-commit-message>
```
```
git push --set-upstream origin new-branch
```

### 5. Update Local Repository
###### While working on a project alongside other contributors, it is important for you to keep your local repository up-to-date with the project as you don’t want to make a pull request for code that will cause conflicts. To keep your local copy of the code base updated, you’ll need to sync changes.
###### We’ll first go over configuring a remote for the fork, then syncing the fork.

### 6. Configure a Remote for the Fork
###### You’ll have to specify a new remote upstream repository for us to sync with the fork. This will be the original repository that you forked from. you’ll have to do this with the git remote add command.
```
git remote add upstream https://github.com/plazzy99/VendorRelief.git
```
###### In this example, // upstream // is the shortname we have supplied for the remote repository since in terms of Git, “upstream” refers to the repository that you cloned from. If you want to add a remote pointer to the repository of a collaborator, you may want to provide that collaborator’s username or a shortened nickname for the shortname.

### 7. Sync the Fork
###### Once you have configured a remote that references the upstream and original repository on GitHub, you are ready to sync your fork of the repository to keep it up-to-date.
To sync your fork, from the directory of your local repository in a terminal window, you’ll have to use the // git fetch // command to fetch the branches along with their respective commits from the upstream repository. Since you used the shortname “upstream” to refer to the upstream repository, you’ll have to pass that to the command:
``` git fetch upstream ```
###### Switch to the local master branch of our repository:
``` git checkout master ```
###### Now merge any changes that were made in the original repository’s master branch, that you will access through your local upstream/master branch, with your local master branch:
``` git merge upstream/master ```

### 8. Create Pull Request
###### At this point, you are ready to make a pull request to the original repository.
###### Navigate to your forked repository, and press the “New pull request” button on your left-hand side of the page.

# ScreenShots
![](https://github.com/plazzy99/VendorRelief/blob/master/app/src/main/res/drawable/userpage.png)
![](https://github.com/plazzy99/VendorRelief/blob/master/app/src/main/res/drawable/authenticationpage.png)
![](https://github.com/plazzy99/VendorRelief/blob/master/app/src/main/res/drawable/verificationpop.png)
![](https://github.com/plazzy99/VendorRelief/blob/master/app/src/main/res/drawable/vendordetailupdateform.png)
![](https://github.com/plazzy99/VendorRelief/blob/master/app/src/main/res/drawable/faqpage.png)
![](https://github.com/plazzy99/VendorRelief/blob/master/app/src/main/res/drawable/spreaddata.png)

## Link to the Website Repository
[Vendor-Relief -> Banke Agarwal](https://github.com/bankebihariagrawal/Vendor-Relief)

## Download app from the link
[Download both the files and then click .apk file](https://drive.google.com/drive/folders/1QqwhVzrsNaS_Xg9V-xUP0fGNDgplSoUH?usp=sharing)

## Project Maintainer
[![Issues](https://img.shields.io/github/issues/plazzy99/VendorRelief)](https://github.com/plazzy99) [![Maintenance](https://img.shields.io/maintenance/yes/2020?color=green&logo=github)](https://github.com/plazzy99)

| <a href="https://github.com/plazzy99"><img src="https://avatars2.githubusercontent.com/u/48295138?s=400&u=8e588b8e5d6e8eb2f40886398c26e37b83b33e50&v=4" width=150px height=150px /></a>                                                                                         |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| **[Vatsal Kesarwani](https://www.linkedin.com/in/vatsal-kesarwani/)**                                                                                                                                        |
| <a href="https://twitter.com/KesarwaniVatsal"><img src="https://openvisualfx.com/wp-content/uploads/2019/10/pnglot.com-twitter-bird-logo-png-139932.png" width="32px" height="32px"></a>  <a href="https://www.linkedin.com/in/vatsal-kesarwani/"><img src="https://mpng.subpng.com/20180324/vhe/kisspng-linkedin-computer-icons-logo-social-networking-ser-facebook-5ab6ebfe5f5397.2333748215219374063905.jpg" width="32px" height="32px"></a> |

> **_Need help?_** 
> **_Feel free to contact me @ [vatsalkesarwani12@gmail.com](mailto:vatsalkesarwani12@gmail@gmail.com?Subject=VendorRelief (KWoC Contributor))_**

## You just made your Firt pull request to Heal-O-Chat
## Do Star ⭐ this Repo.

![Java](https://img.shields.io/badge/java-%230095D5.svg?&style=for-the-badge&logo=java&logoColor=white)
![Git](https://img.shields.io/badge/git%20-%23F05033.svg?&style=for-the-badge&logo=git&logoColor=white)
![Github](https://img.shields.io/badge/github%20-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white)

##### Made with ❤️ By Vatsal Kesarwani
![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)
![ForTheBadge ANDROID](https://forthebadge.com/images/badges/built-for-android.svg)
