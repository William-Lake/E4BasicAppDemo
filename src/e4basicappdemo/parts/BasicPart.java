package e4basicappdemo.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * BasicPart
 * <p>
 * The primary custom part seen when a user starts the app.
 * <p>
 * Currently displays a SWT Label object, which when clicked changes it's text
 * two one of two options, as determined by a combined MouseListener/MouseAdapter.
 * <p>
 * Of note is <code>parent.layout()</code> inside the MouseListener applied to the
 * Label, after the switch statement. Whenever this line is called it tells the 
 * parent composite to lay itself out again, in turn applying any changes to it 
 * (I.e. the text change to the Label object).
 * 
 */
public class BasicPart
{
  //***VARIABLES***
  private static final String TEXTOPTION1 = " Text Option One ";
  private static final String TEXTOPTION2 = " Text Option Two ";
  
  @Inject
  public BasicPart()
  {
    
  }
  
  /**
   * Builds the UI for this part.
   * 
   * @param parent
   *            The parent Composite object on which to build this part's UI.
   */
  @PostConstruct
  public void postConstruct(Composite parent)
  {
    //***INSTANTIATE CONTROLS***
    Label lblClickMe = new Label(parent, SWT.NONE);
    
    //***SET TEXT***
    lblClickMe.setText("Click Me");
    
    //***LISTENERS***
    lblClickMe.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseDown(MouseEvent arg0)
      {
        switch (lblClickMe.getText())
        {
          case TEXTOPTION1:
            lblClickMe.setText(TEXTOPTION2);
            break;
          case TEXTOPTION2:
          default:
            lblClickMe.setText(TEXTOPTION1);
            break;
        }
        parent.layout();
      }
    });
    
    //***LAYOUT***
    FormLayout layout = new FormLayout();
    parent.setLayout(layout);
    
    FormData fd = new FormData();
    fd.top = new FormAttachment(25,0);
    fd.left = new FormAttachment(25,0);
    lblClickMe.setLayoutData(fd);
  }
}
