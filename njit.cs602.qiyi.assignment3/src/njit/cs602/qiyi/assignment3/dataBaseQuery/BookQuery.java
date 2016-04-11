package njit.cs602.qiyi.assignment3.dataBaseQuery;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * <p>
 * BookQuery
 * </p>
 *
 * @author qiyi
 * @version 2016-4-10
 */
public class BookQuery extends JFrame {

    private static final long serialVersionUID = 1L;
    // default queries

    private static final String DEFAULT_QUERY = "SELECT * FROM authors";
    private static final String DEFAULT_QUERY2 = "SELECT title, copyright, titles.isbn FROM authors, titles, authorisbn where authors.authorID = authorisbn.authorID and authorisbn.isbn = titles.isbn";
    private static final String DEFAULT_QUERY3 = "SELECT lastName, firstName FROM authors, titles, authorisbn where authors.authorID = authorisbn.authorID and authorisbn.isbn = titles.isbn";
    private static final String DEFAULT_QUERY4 = "SELECT lastName, firstName, count(*) FROM authors, titles, authorisbn where authors.authorID = authorisbn.authorID and authorisbn.isbn = titles.isbn group by lastName, firstName order by lastName, firstName";
    private static final String[] DEFAULT_QUERYS_NAME = new String[] {
            "All Authors", "List books by author", "List authors by book",
            "List the number of the books published by each author" };
    private static final String[] DEFAULT_QUERYS = new String[] { DEFAULT_QUERY,
            DEFAULT_QUERY2, DEFAULT_QUERY3, DEFAULT_QUERY4 };
    
    private JTextArea queryArea;
    private JScrollPane scrollPane;
    private JComboBox<String> jc;
    private JComboBox<String> jc2;
    private JButton submitButton;
    private JTable resultTable;
    private Box top;
    private Box boxNorth;

    private static ResultSetTableModel tableModel;

    public BookQuery() {
        super();
        initDataModel();
        initGUI();
    }

    private void initDataModel() {
        try {
            tableModel = new ResultSetTableModel(DEFAULT_QUERY);
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
            tableModel.close();
            System.exit(1); // terminate application
        }
    }

    private void initGUI(){
        
        this.setTitle("BOOK QUERY");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 250); 
        
        // ensure database is closed when user quits application
        this.addWindowListener(
           new WindowAdapter() 
           {
              public void windowClosed(WindowEvent event)
              {
                 tableModel.close();
                 System.exit(0);
              } 
           } 
        ); 
        
        // set up JTextArea in which user types queries
        queryArea = new JTextArea(DEFAULT_QUERY, 3, 100);
        queryArea.setWrapStyleWord(true);
        queryArea.setLineWrap(true);
        
        scrollPane = new JScrollPane(queryArea,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
     // set up JButton for submitting queries
        submitButton = new JButton("Submit Query");
        // create event listener for submitButton
        submitButton.addActionListener(        
            new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    try {
                        tableModel.setQuery(queryArea.getText());
                    }
                catch (SQLException sqlException){
                    JOptionPane.showMessageDialog(null, 
                       sqlException.getMessage(), "Database error", 
                       JOptionPane.ERROR_MESSAGE);
                    
                    // try to recover from invalid user query by executing default query
                    try 
                    {
                         tableModel.setQuery(DEFAULT_QUERY);
                         queryArea.setText(DEFAULT_QUERY);
                    } 
                    catch (SQLException sqlException2) 
                    {
                        JOptionPane.showMessageDialog(null, 
                        sqlException2.getMessage(), "Database error", 
                        JOptionPane.ERROR_MESSAGE);
        
                    // ensure database connection is closed
                    tableModel.close();
                    System.exit(1); // terminate application
                    }                 
                 } 
              } 
           });  
        
        jc = new JComboBox<String>(DEFAULT_QUERYS_NAME);
        jc2 = new JComboBox<String>();
        
        jc.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int index = jc.getSelectedIndex();
                if (index == 0 || index == 3){
                    queryArea.setText(DEFAULT_QUERYS[index]);
                    jc2.removeAllItems();
                    String v = "N/A";
                    jc2.addItem(v);
                    jc2.setSelectedItem(v);
                }
                else{
                    Connection cn = null;
                    Statement stat = null;
                    ResultSet rs = null;
                    
                    try{
                        String selectDataBase = "USE " + SystemPara.database;
                        String author = "Select CONCAT(lastName,' ',firstName) from authors order by lastName, firstName";
                        String book = "Select title from titles order by title";
                        String sql = index == 1? author : book;
                        cn = new DataBaseConnection().connection;
                        stat = new DataBaseConnection().connection.createStatement();
                        stat.execute(selectDataBase);
                        rs = stat.executeQuery(sql);
                        jc2.removeAllItems();
                        String v1 = "ALL BOOKS";
                        String v2 = "ALL AUTHORS";
                        jc2.addItem(index == 1? v1 : v2);
                        while(rs.next()) {
                            jc2.addItem(rs.getString(1));
                        }
                        jc2.setSelectedItem(index == 1? v1 : v2);
                    }
                    catch (SQLException sqlException2) 
                    {
                        JOptionPane.showMessageDialog(null, 
                        sqlException2.getMessage(), "Database error", 
                        JOptionPane.ERROR_MESSAGE);
        
                        // ensure database connection is closed
                        tableModel.close();
                        System.exit(1); // terminate application
                    }
                    finally{
                        try{
                            if (stat != null){
                                stat.close();
                            }
                            if (cn != null){
                                cn.close();
                            }
                            if (rs != null){
                                rs.close();
                            }
                        }
                        catch(Exception e2){
                            e2.printStackTrace();
                        }
                    }
                }
            }
        });
        
        jc2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (jc2.getSelectedIndex() != -1){
                    // rewrite sql when select a specific item
                    int index = jc.getSelectedIndex();
                    String sql2 = DEFAULT_QUERYS[index];
                    if (jc2.getSelectedIndex() != 0){
                        if (index == 1){
                            String[] names = ((String)jc2.getSelectedItem()).split(" ");
                            String lname = names[0];
                            String fname = names[1];
                            if (jc2.getSelectedIndex() != 0) sql2 = sql2 + " and lastName = '" + lname + "' and firstName = '" + fname + "' order by lastName, firstName"; 
                            }
                        else  sql2 = sql2 + " and title = '" + (String)jc2.getSelectedItem() + "' order by lastName, firstName"; 
                    }
                    else{
                        if (index == 1) sql2 = "SELECT * FROM titles";
                        else if (index == 2) sql2 = "SELECT * FROM authors"; 
                    }
                    queryArea.setText(sql2);
                    submitButton.doClick();
                }
            }
        });   
        
        // set default selection
        jc.setSelectedItem(DEFAULT_QUERYS_NAME[0]);
        
        top = Box.createVerticalBox();
        top.add(jc);
        top.add(jc2);
        
        boxNorth = Box.createHorizontalBox();
        boxNorth.add(scrollPane);
        boxNorth.add(submitButton);
        
        add(top, BorderLayout.NORTH);
        add(boxNorth, BorderLayout.CENTER);
        
        // create JTable based on the tableModel
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable), BorderLayout.SOUTH);
        
        this.pack();
        this.setVisible(true); 
    }
}
