package cmsc256;

public class MyBook implements Comparable<MyBook> {
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String ISBN10;
    private String ISBN13;


    public MyBook() {
        title = "None Given";
        authorFirstName = "None Given";
        authorLastName = "None Given";
        ISBN10 = "0000000000";
        ISBN13 = "0000000000000";
    }


    public MyBook(String title, String authorFirstName, String authorLastName, String ISBN10, String ISBN13)
    {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.ISBN10 = ISBN10;
        this.ISBN13 = ISBN13;
    }


    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        if(title != null)
        {
            this.title = title;
        }
        else
        {
            throw new IllegalArgumentException("Title not inputted");
        }
    }

    public String getAuthorFirstName()
    {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName)
    {
        if(authorFirstName != null)
        {
            this.authorFirstName = authorFirstName;
        }
        else
        {
            throw new IllegalArgumentException("First name not inputted");
        }
    }

    public String getAuthorLastName()
    {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName)
    {
        if(authorLastName != null)
        {
            this.authorLastName = authorLastName;
        }
        else
        {
            throw new IllegalArgumentException("Last name not inputted");
        }
    }

    public String getISBN10()
    {
        return ISBN10;
    }

    public void setISBN10(String ISBN10)
    {
        boolean number = ISBN10.matches(".*[a-zA-Z].*");

        if(number || ISBN10.length() != 10)
        {
            throw new IllegalArgumentException("The ISBN10 cannot be null or equal to 10 digits");
        }
        else
        {
            this.ISBN10 = ISBN10;
        }
    }

    public String getISBN13()
    {
        return ISBN13;
    }

    public void setISBN13(String ISBN13)
    {
        boolean number = ISBN13.matches(".*[a-zA-Z].*");

        if(number || ISBN13.length() != 13)
        {
            throw new IllegalArgumentException("The ISBN10 cannot be null or equal to 10 digits");
        }
        else
        {
            this.ISBN13 = ISBN13;
        }
    }

    public String toString()
    {
        return "Title: " + title +
                "\nAuthor: " + authorFirstName + " " + authorLastName +
                "\nISBN10: " + ISBN10 +
                "\nISBN13: " + ISBN13;
    }

    public int compareTo(MyBook obj)
    {
        int result = this.authorLastName.compareTo(obj.getAuthorLastName());

        if(result == 0)
        {
            result = this.authorFirstName.compareTo(obj.getAuthorFirstName());
        }

        if (result == 0)
        {
            result = title.compareTo(obj.getTitle());
        }

        return result;
    }

    public boolean equals(Object otherBook)
    {
        if(this == otherBook)
        {
            return true;
        }

        if(!(otherBook instanceof MyBook))
        {
            return false;
        }

        MyBook object = (MyBook) otherBook;

        if(!(ISBN10.equals(object.getISBN10())))
        {
            return false;
        }
        if(!(ISBN13.equals(object.getISBN13())))
        {
            return false;
        }
        return true;
    }
}
