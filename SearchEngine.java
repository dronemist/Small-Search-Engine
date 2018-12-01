public class SearchEngine{
    InvertedPageIndex pageIndex;
    public SearchEngine(){
      pageIndex = new InvertedPageIndex();
    }
    void printPageEntrySet(Myset<PageEntry> m,String[] str)
    {
      node<PageEntry> itr = new node<>();
      if(!m.isEmpty())
        {
          itr = m.front();
          while(itr!=null)
          {
            System.out.print(itr.data.name);
            if(itr.next!=null)
              System.out.print(", ");
            itr = itr.next;
          }
          System.out.println();
        }
    }
    void performAction(String actionMessage)
    {
      String[] words = actionMessage.split(" ");
      int s = words.length;
      try {
      if(words[0].equals("queryFindPagesWhichContainPhrase")||words[0].equals("queryFindPagesWhichContainAllWords")||words[0].equals("queryFindPagesWhichContainAnyOfTheseWords"))
      {
        Myset<PageEntry> m = new Myset<>();
        String str[] = new String[s-1];
        for(int i=1;i<s;i++)
        {
          words[i] = words[i].toLowerCase();
          if(words[i].equalsIgnoreCase("structures"))
            words[i] = "structure";
          else if (words[i].equalsIgnoreCase("applications")) {
            words[i] = "application";
          }
          else if (words[i].equalsIgnoreCase("stacks")) {
            words[i] = "stack";
          }
          str[i-1] = words[i];
        }
        if(words[0].equals("queryFindPagesWhichContainPhrase"))
        {
          try {
            m = pageIndex.getPagesWhichContainPharse(str);
            if(!m.isEmpty())
              {
                printPageEntrySet(m,str);
              }
            else
                  throw new IllegalArgumentException("No Webpage contains phrase ");
          }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        }
        else if(words[0].equals("queryFindPagesWhichContainAllWords"))
        {
          try {
            m = pageIndex.getPagesWhichContainAllWord(str);
            if(!m.isEmpty())
              {
                printPageEntrySet(m, str);
              }
            else
                  throw new IllegalArgumentException("No Webpage contains all words ");
          }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        }
        else if(words[0].equals("queryFindPagesWhichContainAnyOfTheseWords"))
        {
          try {
            m = pageIndex.getPagesWhichContainAnyWord(str);
            if(!m.isEmpty())
              {
                printPageEntrySet(m,str);
              }
            else
                  throw new IllegalArgumentException("No Webpage contains any of these words ");
          }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        }
      }
      else if(s==2)
      {
        if(words[0].equals("addPage"))
        {
          String a = words[1];
          PageEntry p =new PageEntry(a);
          pageIndex.addPage(p);
        }
        else if(words[0].equals("queryFindPagesWhichContainWord"))
        {
          try {
            String a="";
            if(words[1].equalsIgnoreCase("structures"))
              a = "structure";
            else if (words[1].equalsIgnoreCase("applications")) {
              a = "application";
            }
            else if (words[1].equalsIgnoreCase("stacks")) {
              a = "stack";
            }
            else
             a= words[1];
            a = a.toLowerCase();
            Myset<PageEntry> m = new Myset<>();
            m = pageIndex.getPagesWhichContainWord(a);
            //System.out.println(m.size());
            if(!m.isEmpty())
              {
                String st[] = new String[1];
                st[0] = a;
                printPageEntrySet(m,st);
              }
            else
                  throw new IllegalArgumentException("No Webpage contains word "+words[1]);
          } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }

        }
        else
        {
          throw new IllegalArgumentException("Invalid input");
        }
      }
      else if (s==3) {
        if(words[0].equals("queryFindPositionsOfWordInAPage"))
        {
          try {
            String a="";
            if(words[1].equalsIgnoreCase("structures"))
              a = "structure";
            else if (words[1].equalsIgnoreCase("applications")) {
              a = "application";
            }
            else if (words[1].equalsIgnoreCase("stacks")) {
              a = "stack";
            }
            else
             a= words[1];
            a = a.toLowerCase();
            String b = words[2];
            node<PageEntry> itr = new node<>();
            itr = null;
            if(!pageIndex.pageEntryList.isEmpty())
              {
                itr = pageIndex.pageEntryList.front();
                while(itr!=null)
                {
                  if(itr.data.name.equals(b))
                    break;
                  itr = itr.next;
                }
                if(itr==null)
                  throw new IllegalArgumentException("No Webpage "+b+" found");
              }
            else
                throw new IllegalArgumentException("No Webpage "+b+" found");

            PageEntry pi = itr.data;
            node<WordEntry> temp = pi.getPageIndex().list.front();
            while(temp!=null)
            {
              if(temp.data.str.equals(a))
                break;
              temp=temp.next;
            }
            if(temp!=null)
            {
              node<Position> temp2 = temp.data.list.front();
              while(temp2!=null)
              {
                  System.out.print(temp2.data.getWordIndex());
                  if(temp2.next!=null)
                    System.out.print(", ");
                  temp2=temp2.next;
              }
              System.out.println();
          }
            else
            {
              throw new IllegalArgumentException("Webpage "+words[2]+" does not contain word "+words[1]);
            }
          } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }

        }
        else
        {
          throw new IllegalArgumentException("Invalid input");
        }
      }
      else {
        throw new IllegalArgumentException("Invalid input");
      }
    }catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
    }
}
