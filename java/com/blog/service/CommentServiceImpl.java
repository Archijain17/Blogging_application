package com.blog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired 
	private ModelMapper modelmapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
		
		Comment comment=this.modelmapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		Comment savedComment= this.commentRepo.save(comment);
		
		return this.modelmapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","CommentId",commentId));
		this.commentRepo.delete(com);
	}

}
