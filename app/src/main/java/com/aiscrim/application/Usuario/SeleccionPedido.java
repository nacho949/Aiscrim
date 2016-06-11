package com.aiscrim.application.Usuario;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.aiscrim.application.BaseDeDatos.Operaciones;
import com.aiscrim.application.Objetos.DetallePedido;
import com.aiscrim.application.Objetos.ItemCarrito;
import com.aiscrim.application.Objetos.Pedido;
import com.aiscrim.application.Objetos.Usuario;
import com.aiscrim.application.Objetos.Videojuego;
import com.aiscrim.application.R;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class SeleccionPedido extends AppCompatActivity {

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;
    TableLayout table;
    TableRow row;
    TextView producto,precio;
    TextView tx1, tx2;
    float subtotal;
    Pedido pedido;
    Operaciones operacion;
    Button generar, abrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_pedido);

        operacion = new Operaciones(this);
        table = (TableLayout) findViewById(R.id.table_layout_carrito_detalles);
        generar = (Button) findViewById(R.id.generar_factura);
        abrir = (Button) findViewById(R.id.abrir_factura);

        pedido = (Pedido)getIntent().getExtras().getSerializable("parametro");
        agregarToolbar();
        try {
            operacion.consultarDetallePedido(pedido.num);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rellenarTabla();
        comprobarExisteFactura();

        generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPDF();
            }
        });

        abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPdf();
            }
        });
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Pedido número " + pedido.num);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void comprobarExisteFactura() {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Aiscrim/Facturas/Factura_" + pedido.num + ".pdf";
        File dir = new File(path);;

        if(dir.exists()) {
            abrir.setEnabled(true);
        } else {
            abrir.setEnabled(false);
        }
    }

    public void rellenarTabla() {

        DecimalFormat f = new DecimalFormat("0.00");
        for(int i = 0; i< DetallePedido.PEDIDOS_DETALLE.size(); i++) {
            DetallePedido item = DetallePedido.PEDIDOS_DETALLE.get(i);
            row = new TableRow(this);
            producto = new TextView(this);
            precio = new TextView(this);
            producto.setText(item.getNombreProducto() + " " + item.getPlataforma() + " " + item.getTipo() + " x " + item.getCantidad());
            subtotal += item.getPrecioTotal();
            precio.setText(f.format(item.getPrecioTotal()) + " €");
            producto.setLayoutParams(new TableRow.LayoutParams(0));
            precio.setLayoutParams(new TableRow.LayoutParams(2));
            precio.setGravity(Gravity.RIGHT);
            row.addView(producto);
            row.addView(precio);
            table.addView(row);
        }

        View v = new View(this);
        v.setBackgroundColor(Color.DKGRAY);
        v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
        table.addView(v);

        row = new TableRow(this);
        tx1 = new TextView(this);
        tx1.setText("Subtotal");
        tx1.setTextColor(Color.parseColor("#128675"));
        tx1.setLayoutParams(new TableRow.LayoutParams(1));
        tx1.setGravity(Gravity.RIGHT);

        tx2 = new TextView(this);
        tx2.setText(f.format(subtotal) + " €");
        tx2.setLayoutParams(new TableRow.LayoutParams(2));
        //tx2.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        tx2.setGravity(Gravity.RIGHT);

        row.addView(tx1);
        row.addView(tx2);
        table.addView(row);

        row = new TableRow(this);
        tx1 = new TextView(this);
        tx1.setText("Coste envío");
        tx1.setTextColor(Color.parseColor("#128675"));
        tx1.setLayoutParams(new TableRow.LayoutParams(1));
        tx1.setGravity(Gravity.RIGHT);

        tx2 = new TextView(this);
        tx2.setText( "4,99 €");
        tx2.setLayoutParams(new TableRow.LayoutParams(2));
        //tx2.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        tx2.setGravity(Gravity.RIGHT);

        row.addView(tx1);
        row.addView(tx2);
        table.addView(row);

        row = new TableRow(this);
        tx1 = new TextView(this);
        tx1.setText("Total");
        tx1.setTextColor(Color.parseColor("#128675"));
        tx1.setLayoutParams(new TableRow.LayoutParams(1));
        tx1.setGravity(Gravity.RIGHT);

        tx2 = new TextView(this);
        tx2.setText(f.format(subtotal+4.99f) + " €");
        tx2.setLayoutParams(new TableRow.LayoutParams(2));
        //tx2.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        tx2.setGravity(Gravity.RIGHT);

        row.addView(tx1);
        row.addView(tx2);
        table.addView(row);

    }

    public void createPDF()
    {
        DecimalFormat df = new DecimalFormat("0.00");
        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Aiscrim/Facturas";
            File dir = new File(path);
            if(!dir.exists())
                dir.mkdirs();

            File file = new File(dir, "Factura_" + pedido.num + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);

            docWriter = PdfWriter.getInstance(doc, fOut);

            doc.setPageSize(PageSize.LETTER);

            //open the document
            doc.open();

   /* Create Paragraph and Set Font */
            PdfContentByte cb = docWriter.getDirectContent();

            boolean beginPage = true;
            int y = 0;

            for (int i = 0; i < DetallePedido.PEDIDOS_DETALLE.size(); i++) {
                if (beginPage) {
                    beginPage = false;
                    generateLayout(doc, cb);
                    generateHeader(doc, cb);
                    y = 615;
                }
                generateDetail(doc, cb, i, y , DetallePedido.PEDIDOS_DETALLE.get(i));
                y = y - 15;
                if (y < 50) {
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
            }
            printPageNumber(cb);
            doc.newPage();
            beginPage = false;
            cb.rectangle(400, 700, 171, 60);
            cb.moveTo(400, 720);
            cb.lineTo(570, 720);
            cb.moveTo(400, 740);
            cb.lineTo(570, 740);
            cb.moveTo(480, 700);
            cb.lineTo(480, 760);
            cb.stroke();
            createHeadings(cb, 422, 743, "SubTotal:");
            createHeadings(cb, 422, 723, "Gastos envío:");
            createHeadings(cb, 422, 703, "Total:");
            createContent(cb, 542, 743, df.format(subtotal) + " €", PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 542, 723, "4,99 €", PdfContentByte.ALIGN_RIGHT);
            createContent(cb,542,703,df.format(subtotal+4.99f) + " €", PdfContentByte.ALIGN_RIGHT);
            beginPage = true;
            printPageNumber(cb);


        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }

        comprobarExisteFactura();
        Toast.makeText(this, "Factura generada con éxito", Toast.LENGTH_SHORT).show();
    }

    void openPdf()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Aiscrim/Facturas";

        File file = new File(path, "Factura_" + pedido.num + ".pdf");

        intent.setDataAndType( Uri.fromFile( file ), "application/pdf" );
        startActivity(intent);
    }

    private void generateLayout(Document doc, PdfContentByte cb)  {

        try {

            cb.setLineWidth(1f);


            // Invoice Header box Text Headings
            createHeadings(cb,422,743,"Factura n.º:");
            createHeadings(cb, 422, 723, "Fecha:");

            // Invoice Detail box layout
            cb.rectangle(20, 50, 550, 600);
            cb.moveTo(20, 630);
            cb.lineTo(570, 630);

            cb.moveTo(200, 50);
            cb.lineTo(200, 650);

            cb.moveTo(310, 50);
            cb.lineTo(310, 650);

            cb.moveTo(430, 50);
            cb.lineTo(430, 650);

            cb.moveTo(500, 50);
            cb.lineTo(500, 650);
            cb.stroke();

            // Invoice Detail box Text Headings

            createHeadings(cb,22,633,"Producto");
            createHeadings(cb,202,633,"Plataforma");
            createHeadings(cb,312,633,"Tipo");
            createHeadings(cb,432,633,"Cantidad");
            createHeadings(cb,502,633,"Precio Unidad");

            //add the images
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.logo);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image companyLogo = Image.getInstance(stream.toByteArray());
            companyLogo.setAbsolutePosition(25,700);
            companyLogo.scalePercent(30);
            doc.add(companyLogo);

            /*createHeadings(cb, 522, 743, "SubTotal");
            createHeadings(cb, 522, 723, "Gastos de envío:");
            createHeadings(cb, 522, 703, "Total:");*/

        }

        catch (DocumentException dex){
            dex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void generateHeader(Document doc, PdfContentByte cb)  {

        try {

            createHeadings(cb,200,750,"Aiscrim");
            createHeadings(cb,200,735,"Calle Benita Jañez 12");
            createHeadings(cb,200,720,"Leon, 24010");
            createHeadings(cb,200,705,"España");

            createHeadings(cb,472,743,pedido.num + "");
            Calendar calendarInicio = Calendar.getInstance();
            String dia = calendarInicio.get(Calendar.DAY_OF_WEEK) + "";
            String mes = calendarInicio.get(Calendar.MONTH) + "";
            String year = calendarInicio.get(Calendar.YEAR) + "";
            createHeadings(cb,472,723,  dia + "/" + mes + "/" + year);

        }

        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void generateDetail(Document doc, PdfContentByte cb, int index, int y ,DetallePedido pedido)  {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            createContent(cb,25,y,pedido.getNombreProducto(),PdfContentByte.ALIGN_LEFT);
            createContent(cb,205,y, pedido.getPlataforma(),PdfContentByte.ALIGN_LEFT);
            createContent(cb,315,y, pedido.getTipo(),PdfContentByte.ALIGN_LEFT);
            createContent(cb,498,y, pedido.getCantidad() + "",PdfContentByte.ALIGN_RIGHT);
            createContent(cb,568,y, df.format(pedido.getPrecioU()) + " €",PdfContentByte.ALIGN_RIGHT);

        }

        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text){


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void printPageNumber(PdfContentByte cb){


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Página No. " + (pageNumber+1), 570 , 25, 0);
        cb.endText();

        pageNumber++;

    }

    private void createContent(PdfContentByte cb, float x, float y, String text, int align){


        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.showTextAligned(align, text.trim(), x , y, 0);
        cb.endText();

    }

    private void initializeFonts(){


        try {
            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
