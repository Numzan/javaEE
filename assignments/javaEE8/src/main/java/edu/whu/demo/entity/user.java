package edu.whu.demo.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
public class user {
    String name;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private List<Role> roles = new ArrayList<>();
}
