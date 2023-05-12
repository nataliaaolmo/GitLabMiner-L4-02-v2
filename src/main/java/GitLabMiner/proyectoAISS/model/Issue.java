
package GitLabMiner.proyectoAISS.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

    //aunque no viene en la peticion, añadimos la lista de comments pq es necesario para GitMiner y se corresponda con él
    private List<Comment> comments;

    @JsonProperty("id")
    private String id;
    @JsonProperty("issue_iid")
    private String iid;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("state")
    private String state;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private String closedAt;
    @JsonProperty("closed_by")
    private Object closedBy;
    @JsonProperty("labels")
    private List<String> labels;
    @JsonProperty("milestone")
    private Object milestone;
    @JsonProperty("assignees")
    private List<Object> assignees;
    @JsonProperty("author")
    private Author author;
    @JsonProperty("type")
    private String type;
    @JsonProperty("assignee")
    private Author assignee;
    @JsonProperty("user_notes_count")
    private Integer userNotesCount;
    @JsonProperty("merge_requests_count")
    private Integer mergeRequestsCount;
    @JsonProperty("upvotes")
    private Integer upvotes;
    @JsonProperty("downvotes")
    private Integer downvotes;
    @JsonProperty("due_date")
    private Object dueDate;
    @JsonProperty("confidential")
    private Boolean confidential;
    @JsonProperty("discussion_locked")
    private Object discussionLocked;
    @JsonProperty("issue_type")
    private String issueType;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("time_stats")
    private TimeStats timeStats;
    @JsonProperty("task_completion_status")
    private TaskCompletionStatus taskCompletionStatus;
    @JsonProperty("weight")
    private Object weight;
    @JsonProperty("blocking_issues_count")
    private Integer blockingIssuesCount;
    @JsonProperty("has_tasks")
    private Boolean hasTasks;
    @JsonProperty("task_status")
    private String taskStatus;
    @JsonProperty("_links")
    private Links links;
    @JsonProperty("references")
    private References references;
    @JsonProperty("severity")
    private String severity;
    @JsonProperty("moved_to_id")
    private Object movedToId;
    @JsonProperty("service_desk_reply_to")
    private Object serviceDeskReplyTo;
    @JsonProperty("epic_iid")
    private Object epicIid;
    @JsonProperty("epic")
    private Object epic;
    @JsonProperty("iteration")
    private Object iteration;
    @JsonProperty("health_status")
    private Object healthStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public Issue(String issueId, String issueIid, String title, String description, String state,
                 String createdAt, String updatedAt, String closedAt, List<String> labels,
                 Author author, Author assignee, Integer upvotes, Integer downvotes,
                 String webUrl, List<Comment> comments) {
        this.id=issueId;
        this.iid=issueIid;
        this.title=title;
        this.description= description;
        this.state=state;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.closedAt=closedAt;
        this.labels=labels;
        this.author=author;
        this.assignee=assignee;
        this.upvotes=upvotes;
        this.downvotes=downvotes;
        this.webUrl=webUrl;
        this.comments=comments;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("iid")
    public String getIid() {
        return iid;
    }

    @JsonProperty("iid")
    public void setIid(String iid) {
        this.iid = iid;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("closed_at")
    public String getClosedAt() {
        return closedAt;
    }

    @JsonProperty("closed_at")
    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    @JsonProperty("closed_by")
    public Object getClosedBy() {
        return closedBy;
    }

    @JsonProperty("closed_by")
    public void setClosedBy(Object closedBy) {
        this.closedBy = closedBy;
    }

    @JsonProperty("labels")
    public List<String> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonProperty("milestone")
    public Object getMilestone() {
        return milestone;
    }

    @JsonProperty("milestone")
    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    @JsonProperty("assignees")
    public List<Object> getAssignees() {
        return assignees;
    }

    @JsonProperty("assignees")
    public void setAssignees(List<Object> assignees) {
        this.assignees = assignees;
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("assignee")
    public Author getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(Author assignee) {
        this.assignee = assignee;
    }

    @JsonProperty("user_notes_count")
    public Integer getUserNotesCount() {
        return userNotesCount;
    }

    @JsonProperty("user_notes_count")
    public void setUserNotesCount(Integer userNotesCount) {
        this.userNotesCount = userNotesCount;
    }

    @JsonProperty("merge_requests_count")
    public Integer getMergeRequestsCount() {
        return mergeRequestsCount;
    }

    @JsonProperty("merge_requests_count")
    public void setMergeRequestsCount(Integer mergeRequestsCount) {
        this.mergeRequestsCount = mergeRequestsCount;
    }

    @JsonProperty("upvotes")
    public Integer getUpvotes() {
        return upvotes;
    }

    @JsonProperty("upvotes")
    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    @JsonProperty("downvotes")
    public Integer getDownvotes() {
        return downvotes;
    }

    @JsonProperty("downvotes")
    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    @JsonProperty("due_date")
    public Object getDueDate() {
        return dueDate;
    }

    @JsonProperty("due_date")
    public void setDueDate(Object dueDate) {
        this.dueDate = dueDate;
    }

    @JsonProperty("confidential")
    public Boolean getConfidential() {
        return confidential;
    }

    @JsonProperty("confidential")
    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    @JsonProperty("discussion_locked")
    public Object getDiscussionLocked() {
        return discussionLocked;
    }

    @JsonProperty("discussion_locked")
    public void setDiscussionLocked(Object discussionLocked) {
        this.discussionLocked = discussionLocked;
    }

    @JsonProperty("issue_type")
    public String getIssueType() {
        return issueType;
    }

    @JsonProperty("issue_type")
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("time_stats")
    public TimeStats getTimeStats() {
        return timeStats;
    }

    @JsonProperty("time_stats")
    public void setTimeStats(TimeStats timeStats) {
        this.timeStats = timeStats;
    }

    @JsonProperty("task_completion_status")
    public TaskCompletionStatus getTaskCompletionStatus() {
        return taskCompletionStatus;
    }

    @JsonProperty("task_completion_status")
    public void setTaskCompletionStatus(TaskCompletionStatus taskCompletionStatus) {
        this.taskCompletionStatus = taskCompletionStatus;
    }

    @JsonProperty("weight")
    public Object getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(Object weight) {
        this.weight = weight;
    }

    @JsonProperty("blocking_issues_count")
    public Integer getBlockingIssuesCount() {
        return blockingIssuesCount;
    }

    @JsonProperty("blocking_issues_count")
    public void setBlockingIssuesCount(Integer blockingIssuesCount) {
        this.blockingIssuesCount = blockingIssuesCount;
    }

    @JsonProperty("has_tasks")
    public Boolean getHasTasks() {
        return hasTasks;
    }

    @JsonProperty("has_tasks")
    public void setHasTasks(Boolean hasTasks) {
        this.hasTasks = hasTasks;
    }

    @JsonProperty("task_status")
    public String getTaskStatus() {
        return taskStatus;
    }

    @JsonProperty("task_status")
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("references")
    public References getReferences() {
        return references;
    }

    @JsonProperty("references")
    public void setReferences(References references) {
        this.references = references;
    }

    @JsonProperty("severity")
    public String getSeverity() {
        return severity;
    }

    @JsonProperty("severity")
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @JsonProperty("moved_to_id")
    public Object getMovedToId() {
        return movedToId;
    }

    @JsonProperty("moved_to_id")
    public void setMovedToId(Object movedToId) {
        this.movedToId = movedToId;
    }

    @JsonProperty("service_desk_reply_to")
    public Object getServiceDeskReplyTo() {
        return serviceDeskReplyTo;
    }

    @JsonProperty("service_desk_reply_to")
    public void setServiceDeskReplyTo(Object serviceDeskReplyTo) {
        this.serviceDeskReplyTo = serviceDeskReplyTo;
    }

    @JsonProperty("epic_iid")
    public Object getEpicIid() {
        return epicIid;
    }

    @JsonProperty("epic_iid")
    public void setEpicIid(Object epicIid) {
        this.epicIid = epicIid;
    }

    @JsonProperty("epic")
    public Object getEpic() {
        return epic;
    }

    @JsonProperty("epic")
    public void setEpic(Object epic) {
        this.epic = epic;
    }

    @JsonProperty("iteration")
    public Object getIteration() {
        return iteration;
    }

    @JsonProperty("iteration")
    public void setIteration(Object iteration) {
        this.iteration = iteration;
    }

    @JsonProperty("health_status")
    public Object getHealthStatus() {
        return healthStatus;
    }

    @JsonProperty("health_status")
    public void setHealthStatus(Object healthStatus) {
        this.healthStatus = healthStatus;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
