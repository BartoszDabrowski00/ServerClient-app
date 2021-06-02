package streamData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Message implements Serializable {
    private String receiveDate;
    private String sendDate;
    private String username;
    private String content;

    @Override
    public String toString(){
        return receiveDate + " | " + sendDate + " "+username+": "+content;
    }
}
