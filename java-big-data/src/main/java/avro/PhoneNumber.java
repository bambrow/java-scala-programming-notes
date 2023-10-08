/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class PhoneNumber extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6939891958807268935L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PhoneNumber\",\"namespace\":\"avro\",\"fields\":[{\"name\":\"number\",\"type\":\"string\"},{\"name\":\"type\",\"type\":[\"int\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PhoneNumber> ENCODER =
      new BinaryMessageEncoder<PhoneNumber>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PhoneNumber> DECODER =
      new BinaryMessageDecoder<PhoneNumber>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PhoneNumber> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PhoneNumber> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PhoneNumber> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PhoneNumber>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PhoneNumber to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PhoneNumber from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PhoneNumber instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PhoneNumber fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence number;
   private java.lang.Integer type;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PhoneNumber() {}

  /**
   * All-args constructor.
   * @param number The new value for number
   * @param type The new value for type
   */
  public PhoneNumber(java.lang.CharSequence number, java.lang.Integer type) {
    this.number = number;
    this.type = type;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return number;
    case 1: return type;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: number = (java.lang.CharSequence)value$; break;
    case 1: type = (java.lang.Integer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'number' field.
   * @return The value of the 'number' field.
   */
  public java.lang.CharSequence getNumber() {
    return number;
  }


  /**
   * Sets the value of the 'number' field.
   * @param value the value to set.
   */
  public void setNumber(java.lang.CharSequence value) {
    this.number = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public java.lang.Integer getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(java.lang.Integer value) {
    this.type = value;
  }

  /**
   * Creates a new PhoneNumber RecordBuilder.
   * @return A new PhoneNumber RecordBuilder
   */
  public static avro.PhoneNumber.Builder newBuilder() {
    return new avro.PhoneNumber.Builder();
  }

  /**
   * Creates a new PhoneNumber RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PhoneNumber RecordBuilder
   */
  public static avro.PhoneNumber.Builder newBuilder(avro.PhoneNumber.Builder other) {
    if (other == null) {
      return new avro.PhoneNumber.Builder();
    } else {
      return new avro.PhoneNumber.Builder(other);
    }
  }

  /**
   * Creates a new PhoneNumber RecordBuilder by copying an existing PhoneNumber instance.
   * @param other The existing instance to copy.
   * @return A new PhoneNumber RecordBuilder
   */
  public static avro.PhoneNumber.Builder newBuilder(avro.PhoneNumber other) {
    if (other == null) {
      return new avro.PhoneNumber.Builder();
    } else {
      return new avro.PhoneNumber.Builder(other);
    }
  }

  /**
   * RecordBuilder for PhoneNumber instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PhoneNumber>
    implements org.apache.avro.data.RecordBuilder<PhoneNumber> {

    private java.lang.CharSequence number;
    private java.lang.Integer type;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(avro.PhoneNumber.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.number)) {
        this.number = data().deepCopy(fields()[0].schema(), other.number);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing PhoneNumber instance
     * @param other The existing instance to copy.
     */
    private Builder(avro.PhoneNumber other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.number)) {
        this.number = data().deepCopy(fields()[0].schema(), other.number);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'number' field.
      * @return The value.
      */
    public java.lang.CharSequence getNumber() {
      return number;
    }


    /**
      * Sets the value of the 'number' field.
      * @param value The value of 'number'.
      * @return This builder.
      */
    public avro.PhoneNumber.Builder setNumber(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.number = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'number' field has been set.
      * @return True if the 'number' field has been set, false otherwise.
      */
    public boolean hasNumber() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'number' field.
      * @return This builder.
      */
    public avro.PhoneNumber.Builder clearNumber() {
      number = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public java.lang.Integer getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public avro.PhoneNumber.Builder setType(java.lang.Integer value) {
      validate(fields()[1], value);
      this.type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public avro.PhoneNumber.Builder clearType() {
      type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PhoneNumber build() {
      try {
        PhoneNumber record = new PhoneNumber();
        record.number = fieldSetFlags()[0] ? this.number : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.type = fieldSetFlags()[1] ? this.type : (java.lang.Integer) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PhoneNumber>
    WRITER$ = (org.apache.avro.io.DatumWriter<PhoneNumber>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PhoneNumber>
    READER$ = (org.apache.avro.io.DatumReader<PhoneNumber>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.number);

    if (this.type == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeInt(this.type);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.number = in.readString(this.number instanceof Utf8 ? (Utf8)this.number : null);

      if (in.readIndex() != 0) {
        in.readNull();
        this.type = null;
      } else {
        this.type = in.readInt();
      }

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.number = in.readString(this.number instanceof Utf8 ? (Utf8)this.number : null);
          break;

        case 1:
          if (in.readIndex() != 0) {
            in.readNull();
            this.type = null;
          } else {
            this.type = in.readInt();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










