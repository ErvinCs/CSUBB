package Repository;

import Domain.Nota;
import Validator.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NoteRepo extends AbstractRepo<Nota,Map.Entry<String,Integer>> {
    private DocumentBuilderFactory builderFactory;
    public NoteRepo(Validator<Nota> val){
        super(val);
        builderFactory=DocumentBuilderFactory.newInstance();
    }
    private void writeToFile(Nota s, String fd) throws IOException{
        try{
            DocumentBuilder db=builderFactory.newDocumentBuilder();
            Document doc=db.newDocument();
            Element r = doc.createElement("Note");
            Element e = doc.createElement("Nota");
            e.setAttribute("idNota",s.getID().getKey()+s.getID().getValue().toString());
            Element grupa=doc.createElement("nr");
            int n=s.getTema();
            String grr=Integer.toString(n);
            Text gr=doc.createTextNode(grr);
            grupa.appendChild(gr);
            e.appendChild(grupa);
            Element numee=doc.createElement("valoare");
            float val=s.getVal();
            String vall=Float.toString(val);
            Text num=doc.createTextNode(vall);
            numee.appendChild(num);
            e.appendChild(numee);
            Element mail=doc.createElement("predat");
            int pred=s.getPredat();
            String prd=Integer.toString(pred);
            Text email=doc.createTextNode(prd);
            mail.appendChild(email);
            e.appendChild(mail);
            Element dd=doc.createElement("deadline");
            int d=s.getDl();
            String de=Integer.toString(d);
            Text ded=doc.createTextNode(de);
            dd.appendChild(ded);
            e.appendChild(dd);
            Element profesor=doc.createElement("feedback");
            Text prof=doc.createTextNode(fd);
            profesor.appendChild(prof);
            e.appendChild(profesor);
            r.appendChild(e);
            doc.appendChild(r);
            TransformerFactory tf=TransformerFactory.newInstance();
            Transformer t=tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            t.transform(new DOMSource(doc),new StreamResult(new FileOutputStream(s.getStudent().getNume()+".xml",true)));
        }
        catch (Exception e){e.printStackTrace();}
    }
    private void writeToFile2(Nota s) throws IOException{
        try{
            DocumentBuilder db=builderFactory.newDocumentBuilder();
            Document doc=db.newDocument();
            Element r = doc.createElement("Note");
            Element e = doc.createElement("Nota");
            e.setAttribute("idNota",s.getID().getKey()+s.getID().getValue().toString());
            Element st=doc.createElement("nume");
            String stu=s.getStudent().getNume();
            Text std=doc.createTextNode(stu);
            st.appendChild(std);
            e.appendChild(st);
            Element grupa=doc.createElement("nr");
            int n=s.getTema();
            String grr=Integer.toString(n);
            Text gr=doc.createTextNode(grr);
            grupa.appendChild(gr);
            e.appendChild(grupa);
            Element numee=doc.createElement("valoare");
            float val=s.getVal();
            String vall=Float.toString(val);
            Text num=doc.createTextNode(vall);
            numee.appendChild(num);
            e.appendChild(numee);
            r.appendChild(e);
            doc.appendChild(r);
            TransformerFactory tf=TransformerFactory.newInstance();
            Transformer t=tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            t.transform(new DOMSource(doc),new StreamResult(new FileOutputStream("Catalog.xml",true)));
        }
        catch (Exception e){e.printStackTrace();}
    }
    public Nota save(Nota el, String fd) {
        AtomicInteger ok= new AtomicInteger();
        //TODO - use equals() to compare entities
        findAll().forEach(n->{ if(n.getStudent()==el.getStudent() && n.getTema().equals(el.getTema()))  ok.set(1); });
        /**
         for(Nota n:findAll()){
         if(n.getStudent()==el.getStudent() && n.getTema()==el.getTema())
         ok=1;
         }
         */
        if(ok.get() ==0) {
            Nota t = super.save(el);
            try {
                writeToFile(el, fd);
                writeToFile2(el);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            return t;
        }
        else {
            System.out.println("Nota deja existenta");
            return null;
        }
    }

}

