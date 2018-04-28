package erickck.android.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.http.NameValuePair;

import android.net.Uri;


public class EncodeUtils
{
	private static final String URL_ENCODE_CHARSET = "UTF-8";

	public static String encryptionMD5(final String string)
	{
		byte[] hash = null;

		try
		{
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes());
		}
		catch (final NoSuchAlgorithmException e)
		{
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		}
		catch (final Exception e)
		{

		}

		StringBuilder hex = new StringBuilder("");

		if (hash != null)
		{
			hex = new StringBuilder(hash.length * 2);

			for (final byte b : hash)
			{
				final int i = (b & 0xFF);
				if (i < 0x10)
				{
					hex.append('0');
				}
				hex.append(Integer.toHexString(i));
			}
		}
		return hex.toString();
	}

	public static String encryptionSHA1(final String input)
	{
		MessageDigest messageDigest = null;

		try
		{
			messageDigest = MessageDigest.getInstance("SHA-1");
		}
		catch (final Exception e)
		{
			return "";
		}

		final char[] charArray = input.toCharArray();
		final byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
		{
			byteArray[i] = (byte) charArray[i];
		}

		final byte[] MDBytes = messageDigest.digest(byteArray);

		final StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < MDBytes.length; i++)
		{
			final int val = (MDBytes[i]) & 0xff;
			if (val < 16)
			{
				hexValue.append("0");
			}

			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	public static String convertToQueryString(String baseUrl, final List<NameValuePair> args)
	{
		String queryString = "";
		for (final NameValuePair argument : args)
		{
			if (queryString != "")
			{
				queryString += "&";
			}

			try
			{
				queryString += String.format("%s=%s", URLEncoder.encode(argument.getName(), URL_ENCODE_CHARSET),
						URLEncoder.encode(argument.getValue(), URL_ENCODE_CHARSET));
			}
			catch (final UnsupportedEncodingException e)
			{
			}
		}

		final Uri uri = Uri.parse(baseUrl);
		baseUrl = baseUrl + ((uri.getQuery() != null && !uri.getQuery().equals("")) ? "&" : "?") + queryString;

		return baseUrl;
	}

}
