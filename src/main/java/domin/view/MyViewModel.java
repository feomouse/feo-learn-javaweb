package domin.view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class MyViewModel {
    @NotNull
    private Integer id;
    @NotNull
    @Length(max=15, message="长度不可超15")
    @NotBlank
    private String name;
    @Length(max=50, message="长度不可超50")
    @NotBlank
    private String description;
    
    public MyViewModel() {
        
    }
    
    public MyViewModel(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
