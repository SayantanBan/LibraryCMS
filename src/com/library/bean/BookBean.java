package com.library.bean;

import java.io.InputStream;
import java.util.List;
/**
 * This entity is used for adding books in catalogue and  displaying book catalogue and book details
 * @author shreyab
 *
 */
public class BookBean 
{
	private String book_id;
	private String book_name;
	private List<String>author;
	private String publisher;
	private int quantity;
	private String category;
	private InputStream cover;
	private byte[] cover_photo;
	private String[] book_names;
	public String getBookId() 
    {
       return book_id;
	}

    public void setBookId(String newBookId)
    {
    	book_id = newBookId;
    }
    
    public String getBookName() 
    {
       return book_name;
	}

    public void setBookName(String newbookName)
    {
    	book_name = newbookName;
    }
    
    public List<String> getAuthor() 
    {
       return author;
	}

    public void setAuthor(List<String> newAuthor)
    {
    	author = newAuthor;
    }
    
    public String getPublisher() 
    {
       return publisher;
	}

    public void setPublisher(String newPublisher)
    {
    	publisher = newPublisher;
    }
    
    public int getQuantity() 
    {
       return quantity;
	}

    public void setQuantity(int newQuantity)
    {
    	quantity = newQuantity;
    }
    
    public String getCategory() 
    {
       return category;
	}

    public void setCategory(String newCategory)
    {
    	category = newCategory;
    }
    
    public  InputStream  getCover()
    {
    	return cover;
    }
    public void setCover( InputStream  newCover)
    {
    	cover=newCover;
    }
    public  byte[]  getCoverPhoto()
    {
    	return cover_photo;
    }
    public void setCoverPhoto( byte[]  newCover)
    {
    	cover_photo=newCover;
    }
    public String[] getBookNames() 
    {
       return book_names;
	}

    public void setBookNames(String[] newBookNames)
    {
    	book_names = newBookNames;
    }


    
}
