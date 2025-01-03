package finalProject.API.GPT;

import lombok.Data;
import java.util.List;

@Data
public class ChatGPTResponse {
    private long created;
    private Usage usage;
    private String model;
    private String id;
    private List<Choice> choices;
    private String system_fingerprint;
    private String object;

    @Data
    public static class Usage {
        private int completion_tokens;
        private int prompt_tokens;
        private CompletionTokensDetails completion_tokens_details;
        private PromptTokensDetails prompt_tokens_details;
        private int total_tokens;

        @Data
        public static class CompletionTokensDetails {
            private int accepted_prediction_tokens;
            private int audio_tokens;
            private int reasoning_tokens;
            private int rejected_prediction_tokens;
        }

        @Data
        public static class PromptTokensDetails {
            private int audio_tokens;
            private int cached_tokens;
        }
    }

    @Data
    public static class Choice {
        private String finish_reason;
        private int index;
        private Message message;
        private Object logprobs; // null values can be mapped to Object

        @Data
        public static class Message {
            private String role;
            private Object refusal; // null values can be mapped to Object
            private String content;
        }
    }
}
