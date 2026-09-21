What is GitHub ?
 - Collaborative develope platform
 - Public and Private repositories
     * Free, Pro, Teams,, Enterprises
 - Home of Github Copilot (X)
 - Source code control
 - CI/CD automation
 - Package management
 - Collobaration
[![Data Passing Workflow](https://github.com/tbala88/GHActions/actions/workflows/data_passing_workflow.yml/badge.svg)](https://github.com/tbala88/GHActions/actions/workflows/data_passing_workflow.yml)

* Github Actions refers to the entire platform and ecosystem for creating and running automated workflows withing the GitHub environment
* An action is a script invoked from within a workflow
* What are workflows?
    - Automated scrips (actions) that run when specific evenrt occur in your repository
    - Efficient way to automate development tasks
    - Common use cases:
        * CI/CD 
        * Versioning and release management
        * Automation and notifications
* GitHub workflow event Types
    -   Push events
    -   Pull request events
    -   Issue events
    -   Release events
    -   Workflow dispatch events (manual)
    -   Scheduled events
    -   WebHook events (external services) 
Lesson 1: Trigger Custom Workflows
    1.1 Configure Workglows to run for one or more events
on:
  push:
    branches:
     - main
     - develop

    1.2 Configure workflows to run for scheduled events
on:
  schedule:
   - cron: '0 0 * * *' #Everyday at midnight
    1.3 Configure workflows to run for manual events (if you want real human to start it !)
on:
  workflow_dispatch:
    1.4 Configure workflows to run for webhook events
on:
  webhook:
    url: https://example.com/my-webhook
    1.5 Demonstrate a GitHub event to trigger a workflow based on a practical use case


