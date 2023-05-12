package GitLabMiner.proyectoAISS.modelsToGitMiner;


import GitLabMiner.proyectoAISS.model.Issue;

import java.util.List;

public class ProjectGitLab {

    public String id;
    public String name;
    public String web_url;
    private List<CommitGitLab> commits;
    private List<IssueGitLab> issues;

    public ProjectGitLab(String id, String name, String webUrl, List<CommitGitLab> commits, List<IssueGitLab> issues) {
        this.id = id;
        this.name = name;
        this.web_url = webUrl;
        this.commits = commits;
        this.issues = issues;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<CommitGitLab> getCommits() {
        return commits;
    }

    public void setCommits(List<CommitGitLab> commits) {
        this.commits = commits;
    }

    public List<IssueGitLab> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueGitLab> issues) {
        this.issues = issues;
    }

}
