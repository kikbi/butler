package com.example.demo.issue.controller;

import com.example.demo.issue.entity.Issue;
import com.example.demo.issue.vo.PageResult;
import com.example.demo.issue.vo.Pagination;
import com.example.demo.issue.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
@RestController
@RequestMapping(path = "issue")
public class IssueController {

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public ResponseVO<Issue> getIssue(@PathVariable Integer id){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/list")
    public ResponseVO<PageResult<Issue>> listIssues(@RequestBody Pagination pagination){
        return null;
    }


}
