package mobiskif.healthy;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CursorSOAP extends ContentProvider {
    Activity activity;

    public CursorSOAP() {
        super();
    }

    public CursorSOAP(Activity a) {
        super();
        activity = a;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String action = uri.getLastPathSegment();
        //Patient patient = new Patient(activity);
        try {
            return (Cursor) this
                    .getClass()
                    .getMethod(action, String.class, Patient.class)
                    .invoke(this, action, new Patient(activity));
        } catch (Exception e) {
            //L.d(e.toString(),this);
            return defaultList();
        }
    }

    Cursor GetDistrictList(String action, Patient p) {
        String query = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetDistrictList>\n" +
                "         <tem:guid>6b2158a1-56e0-4c09-b70b-139b14ffee14</tem:guid>\n" +
                "      </tem:GetDistrictList>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        XmlPullParser myParser = readSOAP(query, action);

        String[] from = {"_ID", "column1", "column2", "column3"};
        int event;
        String text = null;
        MatrixCursor mc = new MatrixCursor(from);
        Object[] row = new Object[from.length];
        //mc.addRow(row);
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "DistrictName":
                                row[1] = text;
                                break;
                            case "IdDistrict":
                                row[0] = text;
                                mc.addRow(row);
                                break;
                            default:
                                break;
                        }
                        text = null;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            L.d("Ошибка парсинга SOAP " + e.toString(), this);
            return defaultList();
        }
        return mc;
    }

    Cursor GetLPUList(String action, Patient p) {
        String query = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetLPUList>\n" +
                "         <tem:IdDistrict>"+p.getDistrictID()+"</tem:IdDistrict>\n" +
                "         <tem:guid>6b2158a1-56e0-4c09-b70b-139b14ffee14</tem:guid>\n" +
                "      </tem:GetLPUList>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        XmlPullParser myParser = readSOAP(query, action);

        String[] from = {"_ID", "column1", "column2", "column3"};
        int event;
        String text = null;
        MatrixCursor mc = new MatrixCursor(from);
        Object[] row = new Object[from.length];
        //mc.addRow(row);
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "IdLPU":
                                row[0] = text;
                                break;
                            case "LPUFullName":
                                row[1] = text;
                                mc.addRow(row);
                                break;
                            default:
                                break;
                        }
                        text = null;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            L.d("Ошибка парсинга SOAP " + e.toString(), this);
            return defaultList();
        }
        return mc;
    }

    Cursor GetSpesialityList(String action, Patient p) {
        String query = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetSpesialityList>\n" +
                "         <tem:idLpu>"+p.getLPUid()+"</tem:idLpu>\n" +
                "         <tem:idPat>"+p.getIdPat()+"</tem:idPat>\n" +
                "         <tem:guid>6b2158a1-56e0-4c09-b70b-139b14ffee14</tem:guid>\n" +
                "      </tem:GetSpesialityList>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        XmlPullParser myParser = readSOAP(query, action);

        String[] from = {"_ID", "column1", "column2", "column3"};
        int event;
        String text = null;
        MatrixCursor mc = new MatrixCursor(from);
        Object[] row = new Object[from.length];
        //mc.addRow(row);
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "CountFreeParticipantIE":
                                row[2] = text;
                                break;
                            case "IdSpesiality":
                                row[0] = text;
                                break;
                            case "NameSpesiality":
                                row[1] = text;
                                mc.addRow(row);
                                break;
                            default:
                                break;
                        }
                        text = null;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            L.d("Ошибка парсинга SOAP " + e.toString(), this);
            return defaultList();
        }
        return mc;
    }

    Cursor GetDoctorList(String action, Patient p) {
        String query = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetDoctorList>\n" +
                "         <tem:idSpesiality>"+p.getSpesialityID()+"</tem:idSpesiality>\n" +
                "         <tem:idLpu>"+p.getLPUid()+"</tem:idLpu>\n" +
                "         <tem:idPat>"+p.getIdPat()+"</tem:idPat>\n" +
                "         <tem:guid>6b2158a1-56e0-4c09-b70b-139b14ffee14</tem:guid>\n" +
                "      </tem:GetDoctorList>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        XmlPullParser myParser = readSOAP(query, action);

        String[] from = {"_ID", "column1", "column2", "column3"};
        int event;
        String text = null;
        MatrixCursor mc = new MatrixCursor(from);
        Object[] row = new Object[from.length];
        //mc.addRow(row);
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "CountFreeParticipantIE":
                                row[2] = text;
                                break;
                            case "IdDoc":
                                row[0] = text;
                                break;
                            case "Name":
                                row[1] = text;
                                mc.addRow(row);
                                break;
                            default:
                                break;
                        }
                        text = null;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            L.d("Ошибка парсинга SOAP " + e.toString(), this);
            return defaultList();
        }
        return mc;
    }

    Cursor GetAvaibleAppointments(String action, Patient p) {
        String query = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetAvaibleAppointments>\n" +
                "         <tem:idDoc>"+p.getDoctorID()+"</tem:idDoc>\n" +
                "         <tem:idLpu>"+p.getLPUid()+"</tem:idLpu>\n" +
                "         <tem:idPat>"+p.getIdPat()+"</tem:idPat>\n" +
                "         <tem:visitStart>2017-06-25</tem:visitStart>\n" +
                "         <tem:visitEnd>2017-07-30</tem:visitEnd>\n" +
                "         <tem:guid>6b2158a1-56e0-4c09-b70b-139b14ffee14</tem:guid>\n" +
                "      </tem:GetAvaibleAppointments>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        XmlPullParser myParser = readSOAP(query, action);

        String[] from = {"_ID", "column1", "column2", "column3"};
        int event;
        String text = "";
        MatrixCursor mc = new MatrixCursor(from);
        Object[] row = new Object[from.length];
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        int i=0;
                        switch (name) {
                            case "IdAppointment":
                                row[0] = ++i;
                                row[3] = text;
                                break;
                            case "VisitStart":
                                int loc = text.indexOf("T");
                                String date=text.substring(0,loc);
                                String time = text.substring(loc+1);
                                loc=time.lastIndexOf(":");
                                time=time.substring(0,loc);
                                row[1] = date;
                                row[2] = time;
                                mc.addRow(row);
                                break;
                            default:
                                break;
                        }
                        text = null;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            L.d("Ошибка парсинга SOAP " + e.toString(), this);
            return defaultList();
        }
        return mc;
    }

    Cursor CheckPatient(String action, Patient p) {
        String query = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\" xmlns:hub=\"http://schemas.datacontract.org/2004/07/HubService2\">" +
                "<soapenv:Header/><soapenv:Body><tem:CheckPatient><tem:pat>\n" +
                "<hub:Birthday>"+p.getBirstdate()+"</hub:Birthday>\n" +
                "<hub:Name>"+p.getName()+"</hub:Name>\n" +
                "<hub:Surname>"+p.getSurname()+"</hub:Surname></tem:pat>\n" +
                "<tem:idLpu>"+p.getLPUid()+"</tem:idLpu>\n" +
                "<tem:guid>6b2158a1-56e0-4c09-b70b-139b14ffee14</tem:guid>\n" +
                "</tem:CheckPatient></soapenv:Body></soapenv:Envelope>\n";

        XmlPullParser myParser = readSOAP(query, action);

        String[] from = {"_ID", "column1", "column2", "column3"};
        int event;
        String text = null;
        MatrixCursor mc = new MatrixCursor(from);
        Object[] row = new Object[from.length];
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "IdPat":
                                row[0] = text;
                                //row[1] = text;
                                mc.addRow(row);
                                break;
                            default:
                                break;
                        }
                        text = null;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            L.d("Ошибка парсинга SOAP " + e.toString(), this);
            return defaultList();
        }
        return mc;
    }

    Cursor GetOrgList(String action, Patient p) {
        String query = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetOrgList>\n" +
                "         <tem:IdDistrict>" + p.getDistrictID() + "</tem:IdDistrict>\n" +
                "         <tem:guid>6b2158a1-56e0-4c09-b70b-139b14ffee14</tem:guid>\n" +
                "      </tem:GetOrgList>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        XmlPullParser myParser = readSOAP(query, action);
        if (myParser==null) return defaultList();
        else {

            String[] from = {"_ID", "column1", "column2", "column3"};
            int event;
            String text = null;
            MatrixCursor mc = new MatrixCursor(from);
            Object[] row = new Object[from.length];
            try {
                event = myParser.getEventType();
                while (event != XmlPullParser.END_DOCUMENT) {
                    String name = myParser.getName();
                    switch (event) {
                        case XmlPullParser.START_TAG:
                            break;

                        case XmlPullParser.TEXT:
                            text = myParser.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            switch (name) {
                                case "Chief":
                                    break;
                                case "Contact":
                                    break;
                                case "Distric":
                                    break;
                                case "EMail":
                                    break;
                                case "Hub_ID":
                                    row[0] = text;
                                    break;
                                case "ID":
                                    break;
                                case "Org_Address":
                                    row[3] = text;
                                    break;
                                case "Org_Name":
                                    row[1] = text;
                                    break;
                                case "Org_Type":
                                    row[2] = null;
                                    if (row[0] != null) mc.addRow(row);
                                    break;
                                case "WWW":
                                    break;
                                default:
                                    break;
                            }
                            text = null;
                    }
                    event = myParser.next();
                }
            } catch (Exception e) {
                L.d("Ошибка парсинга SOAP " + e.toString(), this);
                return defaultList();
            }
            return mc;
        }
    }

    XmlPullParser readSOAP(String body, String action) {
        try {
            URL url = new URL("https://api.gorzdrav.spb.ru/Service/HubService.svc");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            conn.setRequestProperty("SOAPAction", "http://tempuri.org/IHubService/" + action);
            conn.setRequestProperty("Content-Length", String.valueOf(body.getBytes().length));
            conn.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
            conn.setDoOutput(true);

            //передача запроса
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            outputStream.write(body);
            outputStream.flush();
            outputStream.close();
            L.d("Запрос= " + body.getBytes().length + " bytes, \n" + body, this);

            //чтение ответа
            conn.connect();
            String line;
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) sb.append(line);
            isr.close();
            reader.close();
            L.d("Ответ= " + sb.length() + " bytes, " + sb, this);

            //препарсинг
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);// включаем поддержку namespace (по умолчанию выключена)
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(sb.toString()));
            return xpp;
        } catch (Exception e) {
            L.d("Ошибка чтения SOAP " + e.toString(), this);
            return null;
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public Cursor defaultList() {
        String[] from = {"_ID", "1", "2", "3"};
        MatrixCursor mc = new MatrixCursor(from);
        Object[] from1 = {174, "Вы не зарегистрированы в клинике или проверьте \"Настройки\"", "000", "город Санкт-Петербург, Ленинский проспект, 88"};
        Object[] from2 = {175, "Вы не зарегистрированы в этой клинике или проверьте \"Настройки\"", "000", "город Санкт-Петербург, Ленинский проспект, 95"};
        Object[] from3 = {176, "Вы зарегистрированы в этой клинике? Проверьте \"Настройки\"", "000", "город Санкт-Петербург, улица Десантников, 104"};
        Object[] from4 = {177, "А вы зарегистрированы в этой больнице? Проверьте \"Настройки\"", "000", "город Санкт-Петербург, Ленинский проспект, 88"};
        mc.addRow(from1);
        mc.addRow(from2);
        mc.addRow(from3);
        mc.addRow(from4);
        return mc;
    }

}
