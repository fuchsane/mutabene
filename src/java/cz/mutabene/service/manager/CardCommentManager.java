/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.CardCommentEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("cardCommentManager")
public class CardCommentManager extends GenericDataManager<CardCommentEntity> {

    public boolean add(CardCommentEntity object) {
        try{
        hibTempl.save(object);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(CardCommentEntity object) {
        try{
            CardCommentEntity cardComment = findById(object.getId());
            cardComment.setText(object.getText());
            cardComment.setDateOfComment(object.getDateOfComment());
            cardComment.setCard(object.getCard());
            cardComment.setUser(object.getUser());
            hibTempl.update(cardComment);
            hibTempl.flush();
            return true;   
        } catch (Exception e){
            return false;
        }
    }

    public boolean delete(CardCommentEntity object) {
        try{
            hibTempl.delete(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public CardCommentEntity findById(Long id) {
        try{
         List<CardCommentEntity> cardComment = hibTempl.find("from CardCommentEntity as cardComment where cardComment.id like ? ", id);
         if(!cardComment.isEmpty()){
         return cardComment.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    public Collection<CardCommentEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<CardCommentEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<CardCommentEntity> findAll() {
        return hibTempl.loadAll(CardCommentEntity.class);
    }
    
    public Collection<CardCommentEntity> findByIdCard(Long id){
     try{
        List<CardCommentEntity> cardComment = hibTempl.find("from CardCommentEntity as cardComment where cardComment.card.id like ? ", id);
        return cardComment;
     }catch(Exception e){
         return null;
     }
    }
    
}
