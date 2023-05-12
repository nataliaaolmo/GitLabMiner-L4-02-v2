package GitLabMiner.proyectoAISS.modelsToGitMiner;

public class CommentGitLab {

        private String id;
        private String body;
        private UserGitLab author;
        private String created_at;
        private String updated_at;

        public CommentGitLab(String id, String body, UserGitLab author, String created_at, String updated_at) {
            this.id = id;
            this.body = body;
            this.author = author;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public UserGitLab getAuthor() {
            return author;
        }

        public void setAuthor(UserGitLab author) {
            this.author = author;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }

