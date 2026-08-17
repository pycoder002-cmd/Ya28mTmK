package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class FilteringParserDelegate extends JsonParserDelegate {
    protected boolean _allowMultipleMatches;
    protected JsonToken _currToken;
    protected TokenFilterContext _exposedContext;
    protected TokenFilterContext _headContext;

    @Deprecated
    protected boolean _includeImmediateParent;
    protected boolean _includePath;
    protected TokenFilter _itemFilter;
    protected JsonToken _lastClearedToken;
    protected int _matchCount;
    protected TokenFilter rootFilter;

    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, boolean z, boolean z2) {
        super(jsonParser);
        this.rootFilter = tokenFilter;
        this._itemFilter = tokenFilter;
        this._headContext = TokenFilterContext.createRootContext(tokenFilter);
        this._includePath = z;
        this._allowMultipleMatches = z2;
    }

    private JsonToken _nextBuffered(TokenFilterContext tokenFilterContext) throws IOException {
        this._exposedContext = tokenFilterContext;
        JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
        if (nextTokenToRead != null) {
            return nextTokenToRead;
        }
        while (tokenFilterContext != this._headContext) {
            tokenFilterContext = this._exposedContext.findChildOf(tokenFilterContext);
            this._exposedContext = tokenFilterContext;
            if (tokenFilterContext == null) {
                throw _constructError("Unexpected problem: chain of filtered context broken");
            }
            JsonToken nextTokenToRead2 = this._exposedContext.nextTokenToRead();
            if (nextTokenToRead2 != null) {
                return nextTokenToRead2;
            }
        }
        throw _constructError("Internal error: failed to locate expected buffered tokens");
    }

    protected JsonStreamContext _filterContext() {
        return this._exposedContext != null ? this._exposedContext : this._headContext;
    }

    /* JADX WARN: Code restructure failed: missing block: B:138:0x015e, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0160, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final com.fasterxml.jackson.core.JsonToken _nextToken2() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.filter.FilteringParserDelegate._nextToken2():com.fasterxml.jackson.core.JsonToken");
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x0134, code lost:
    
        return _nextBuffered(r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final com.fasterxml.jackson.core.JsonToken _nextTokenWithBuffering(com.fasterxml.jackson.core.filter.TokenFilterContext r6) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.filter.FilteringParserDelegate._nextTokenWithBuffering(com.fasterxml.jackson.core.filter.TokenFilterContext):com.fasterxml.jackson.core.JsonToken");
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final int currentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getBooleanValue() throws IOException {
        return this.delegate.getBooleanValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getCurrentName() throws IOException {
        JsonStreamContext _filterContext = _filterContext();
        if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY) {
            return _filterContext.getCurrentName();
        }
        JsonStreamContext parent = _filterContext.getParent();
        if (parent == null) {
            return null;
        }
        return parent.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final int getCurrentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }

    public TokenFilter getFilter() {
        return this.rootFilter;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }

    public int getMatchCount() {
        return this._matchCount;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonStreamContext getParsingContext() {
        return _filterContext();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getText() throws IOException {
        return this.delegate.getText();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() throws IOException {
        return this.delegate.getTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() throws IOException {
        return this.delegate.getTextLength();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextOffset() throws IOException {
        return this.delegate.getTextOffset();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean() throws IOException {
        return this.delegate.getValueAsBoolean();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean(boolean z) throws IOException {
        return this.delegate.getValueAsBoolean(z);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble() throws IOException {
        return this.delegate.getValueAsDouble();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble(double d) throws IOException {
        return this.delegate.getValueAsDouble(d);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) throws IOException {
        return this.delegate.getValueAsInt(i);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j) throws IOException {
        return this.delegate.getValueAsLong(j);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException {
        return this.delegate.getValueAsString();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException {
        return this.delegate.getValueAsString(str);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i) {
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? i == 0 : jsonToken.id() == i;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x011a, code lost:
    
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0123, code lost:
    
        if (r1 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0125, code lost:
    
        r1 = r1.filterStartArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0129, code lost:
    
        r5._itemFilter = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x012d, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x012f, code lost:
    
        r5._headContext = r5._headContext.createChildArrayContext(r1, true);
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0139, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x013a, code lost:
    
        r5._headContext = r5._headContext.createChildArrayContext(r1, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0144, code lost:
    
        if (r5._includePath == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0146, code lost:
    
        r0 = _nextTokenWithBuffering(r5._headContext);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x014c, code lost:
    
        if (r0 == null) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x014e, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0150, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0151, code lost:
    
        r1 = r5._headContext.isStartHandled();
        r2 = r5._headContext.getFilter();
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x015d, code lost:
    
        if (r2 == null) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0161, code lost:
    
        if (r2 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0163, code lost:
    
        r2.filterFinishArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0166, code lost:
    
        r5._headContext = r5._headContext.getParent();
        r5._itemFilter = r5._headContext.getFilter();
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0176, code lost:
    
        if (r1 == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0178, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x017a, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x017b, code lost:
    
        r1 = r5._itemFilter;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x017f, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0181, code lost:
    
        r5._headContext = r5._headContext.createChildObjectContext(r1, true);
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x018b, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x018c, code lost:
    
        if (r1 != null) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x018e, code lost:
    
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0194, code lost:
    
        r1 = r5._headContext.checkValue(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x019a, code lost:
    
        if (r1 != null) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x019c, code lost:
    
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x01a4, code lost:
    
        if (r1 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x01a6, code lost:
    
        r1 = r1.filterStartObject();
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x01aa, code lost:
    
        r5._itemFilter = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01ae, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x01b0, code lost:
    
        r5._headContext = r5._headContext.createChildObjectContext(r1, true);
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x01ba, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x01bb, code lost:
    
        r5._headContext = r5._headContext.createChildObjectContext(r1, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x01c5, code lost:
    
        if (r5._includePath == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x01c7, code lost:
    
        r0 = _nextTokenWithBuffering(r5._headContext);
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x01cd, code lost:
    
        if (r0 == null) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x01cf, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01d1, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x003f, code lost:
    
        if (r0 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0041, code lost:
    
        r2 = r0.nextTokenToRead();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0045, code lost:
    
        if (r2 == null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004c, code lost:
    
        if (r0 != r5._headContext) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005f, code lost:
    
        r0 = r5._headContext.findChildOf(r0);
        r5._exposedContext = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0067, code lost:
    
        if (r0 != null) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006f, code lost:
    
        throw _constructError("Unexpected problem: chain of filtered context broken");
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x004e, code lost:
    
        r5._exposedContext = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0054, code lost:
    
        if (r0.inArray() == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0056, code lost:
    
        r0 = r5.delegate.getCurrentToken();
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x005e, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0047, code lost:
    
        r5._currToken = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0049, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0070, code lost:
    
        r0 = r5.delegate.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0076, code lost:
    
        if (r0 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0078, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x007a, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0081, code lost:
    
        switch(r0.id()) {
            case 1: goto L109;
            case 2: goto L100;
            case 3: goto L78;
            case 4: goto L100;
            case 5: goto L50;
            default: goto L46;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0084, code lost:
    
        r1 = r5._itemFilter;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0088, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008a, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x008c, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01d2, code lost:
    
        if (r1 == null) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d4, code lost:
    
        r1 = r5._headContext.checkValue(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dc, code lost:
    
        if (r1 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01de, code lost:
    
        if (r1 == null) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01e6, code lost:
    
        if (r1.includeValue(r5.delegate) == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01e8, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01ea, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01ef, code lost:
    
        return _nextToken2();
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x008d, code lost:
    
        r1 = r5.delegate.getCurrentName();
        r2 = r5._headContext.setFieldName(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x009b, code lost:
    
        if (r2 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x009d, code lost:
    
        r5._itemFilter = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00a1, code lost:
    
        if (r5._includePath != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00a5, code lost:
    
        if (r5._includeImmediateParent == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00ad, code lost:
    
        if (r5._headContext.isStartHandled() != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00af, code lost:
    
        r0 = r5._headContext.nextTokenToRead();
        r5._exposedContext = r5._headContext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00b9, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00bb, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00bc, code lost:
    
        if (r2 != null) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00be, code lost:
    
        r5.delegate.nextToken();
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00ca, code lost:
    
        r1 = r2.includeProperty(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00ce, code lost:
    
        if (r1 != null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00d0, code lost:
    
        r5.delegate.nextToken();
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00dc, code lost:
    
        r5._itemFilter = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00e0, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00e4, code lost:
    
        if (r5._includePath == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00e6, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00e8, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00eb, code lost:
    
        if (r5._includePath == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00ed, code lost:
    
        r0 = _nextTokenWithBuffering(r5._headContext);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00f3, code lost:
    
        if (r0 == null) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00f5, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00f7, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00f8, code lost:
    
        r1 = r5._itemFilter;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x00fc, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00fe, code lost:
    
        r5._headContext = r5._headContext.createChildArrayContext(r1, true);
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0108, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0109, code lost:
    
        if (r1 != null) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x010b, code lost:
    
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0112, code lost:
    
        r1 = r5._headContext.checkValue(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0118, code lost:
    
        if (r1 != null) goto L87;
     */
    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.fasterxml.jackson.core.JsonToken nextToken() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.filter.FilteringParserDelegate.nextToken():com.fasterxml.jackson.core.JsonToken");
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() throws IOException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public void overrideCurrentName(String str) {
        throw new UnsupportedOperationException("Can not currently override name during filtering read");
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() throws IOException {
        if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            JsonToken nextToken = nextToken();
            if (nextToken == null) {
                return this;
            }
            if (nextToken.isStructStart()) {
                i++;
            } else if (nextToken.isStructEnd() && i - 1 == 0) {
                return this;
            }
        }
    }
}
