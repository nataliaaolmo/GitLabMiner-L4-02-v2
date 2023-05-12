
package GitLabMiner.proyectoAISS.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeStats {

    @JsonProperty("time_estimate")
    private Integer timeEstimate;
    @JsonProperty("total_time_spent")
    private Integer totalTimeSpent;
    @JsonProperty("human_time_estimate")
    private Object humanTimeEstimate;
    @JsonProperty("human_total_time_spent")
    private Object humanTotalTimeSpent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("time_estimate")
    public Integer getTimeEstimate() {
        return timeEstimate;
    }

    @JsonProperty("time_estimate")
    public void setTimeEstimate(Integer timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    @JsonProperty("total_time_spent")
    public Integer getTotalTimeSpent() {
        return totalTimeSpent;
    }

    @JsonProperty("total_time_spent")
    public void setTotalTimeSpent(Integer totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    @JsonProperty("human_time_estimate")
    public Object getHumanTimeEstimate() {
        return humanTimeEstimate;
    }

    @JsonProperty("human_time_estimate")
    public void setHumanTimeEstimate(Object humanTimeEstimate) {
        this.humanTimeEstimate = humanTimeEstimate;
    }

    @JsonProperty("human_total_time_spent")
    public Object getHumanTotalTimeSpent() {
        return humanTotalTimeSpent;
    }

    @JsonProperty("human_total_time_spent")
    public void setHumanTotalTimeSpent(Object humanTotalTimeSpent) {
        this.humanTotalTimeSpent = humanTotalTimeSpent;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
