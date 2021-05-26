package com.webblog.service.ServiceImpl;

import com.webblog.model.Likes;
import com.webblog.model.Person;
import com.webblog.model.Post;
import com.webblog.repository.LikesRepository;
import com.webblog.repository.PostRepository;
import com.webblog.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikesService {

    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private PostRepository postRepository;

    /**
     * CREATE operation on Comment
     * @param person
     * @param postId
     * @param action
     * @return boolean
     * */
    public boolean likePost(Person person, Long postId, String action){
        boolean result = false;

        Post post = postRepository.findById(postId).get();

        try{
            Likes like = new Likes();
            like.setPerson(person);
            like.setPost(post);

            if(action.equals("1")){
                likesRepository.save(like);
                System.out.println("save");
            }else{
                likesRepository.deleteLikesByPostAndPerson(post,person);
                System.out.println("delete");
            }

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
